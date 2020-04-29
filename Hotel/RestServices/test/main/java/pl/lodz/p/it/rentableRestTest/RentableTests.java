package pl.lodz.p.it.rentableRestTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;

@Testcontainers
public class RentableTests {
    private static Client client = ClientBuilder.newClient();
    private static final Logger LOGGER = LoggerFactory.getLogger(RentableTests.class);

    @Container
    public static GenericContainer restService = new GenericContainer(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(builder
                            -> builder
                            .from("payara/micro:jdk11")
                            .copy("UIControllers.war", "/opt/payara/deployments")
                            .build())
                    .withFileFromPath("UIControllers.war", Path.of("../UserInterface/UIControllers/target", "UIControllers-1.0-SNAPSHOT.war"))
    )
            .withExposedPorts(8080, 4848, 6900)
            .waitingFor(Wait.forHttp("/Store/resources/model/rentables").forPort(8080).forStatusCode(200))
            .withLogConsumer(new Slf4jLogConsumer(LOGGER));

    @BeforeAll
    static public void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = restService.getMappedPort(8080);
        RestAssured.basePath = "/Store/resources/model/";
        System.out.println("Docker mapped port: "
                + restService.getMappedPort(8080));
    }

    @Test
    public void testGetRentables() {
    RestAssured.get("rentables")
            .then()
            .assertThat()
            .statusCode(is(200));
    }

    @Test
    public void testGetRentable() {
        RestAssured.get("rentable/2")
                .then()
                .assertThat()
                .body("number", is(2))
                .body("area", is(30.0f))
                .body("beds", is(1));
    }

    @Test
    public void testGetNonexistentRentable() {
        Response r = RestAssured
                .get("rentable/1232");

        Assert.assertEquals(r.getStatusCode(),404);
    }

    @Test
    public void testAddRoom() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("number",31);
        requestParams.put("area",200);
        requestParams.put("beds",4);

        Response r = RestAssured
                .given()
                .contentType("application/json")
                .body(requestParams.toString())
                .post("room");

        Assert.assertEquals(r.statusCode(),200);

        Response r2 = RestAssured
                .given()
                .contentType("application/json")
                .body(requestParams.toString())
                .post("room");
        Assert.assertEquals(r2.statusCode(),403);
    }

    @Test
    public void testDeleteRentable() {
        Response r = RestAssured
                .given()
                .contentType("application/json")
                .delete("rentable/20");

        Assert.assertEquals(r.statusCode(),200);

        Response r2 = RestAssured
                .given()
                .contentType("application/json")
                .delete("rentable/20");

        Assert.assertEquals(r2.statusCode(),404);
    }

    @Test
    public void testUpdateRoom() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("number",3);
        requestParams.put("area",200);
        requestParams.put("beds",4);

        Response r = RestAssured
                .given()
                .contentType("application/json")
                .body(requestParams.toString())
                .put("room/3");

        Assert.assertEquals(r.statusCode(),200);
    }

    @Test
    public void testTryUpdateNonexistentRoom() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("number",122);
        requestParams.put("area",200);
        requestParams.put("beds",4);

        Response r = RestAssured
                .given()
                .contentType("application/json")
                .body(requestParams.toString())
                .put("room/122");

        Assert.assertEquals(r.asString(),"Room doesn't exist");
        Assert.assertEquals(r.statusCode(),404);
    }



    @AfterAll
    public static void closeDocker(){
        restService.stop();
    }
}

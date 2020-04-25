package pl.lod.p.it.RentTests;

import io.restassured.RestAssured;
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
                    .withFileFromPath("UIControllers.war", Path.of("target", "UIControllers-1.0-SNAPSHOT.war"))
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
            .body("size()", is(7));
    }

    @AfterAll
    public static void closeDocker(){
        restService.stop();
    }
}

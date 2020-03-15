package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named(value = "restAddRentableController")
@ConversationScoped
public class RestAddRentableController implements Serializable {

    @Inject
    private Conversation conversation;
    
    private HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(request.getRequestURL()
           .substring(0, (request.getRequestURL().length() - request.getServletPath().length())).concat("/resources/model"));
    
    private Room room;
    private Sauna sauna;
    
    private String number;
    private String area;
    private String beds;
    
    private String price;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public RestAddRentableController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new Room();
        sauna = new Sauna();
    }
    
    public Room getRoom() {
        return room;
    }
    
    public Sauna getSauna() {
        return sauna;
    }
    
    public String addRoom() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        room.setNumber(Integer.parseInt(number));
        room.setArea(Double.parseDouble(area));
        room.setBeds(Integer.parseInt(beds));
        return "RestAddRoom";
    }
    
    public String addRoomConfirm() {
        webTarget.path("room").request(MediaType.APPLICATION_JSON).post(Entity.json(this.room), Response.class);
        conversation.end();
        return "home";
    }
    
    public String addSauna() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        sauna.setNumber(Integer.parseInt(number));
        sauna.setPricePerHour(Double.parseDouble(price));
        return "RestAddSauna";
    }
    
    public String addSaunaConfirm() {
        webTarget.path("sauna").request(MediaType.APPLICATION_JSON).post(Entity.json(this.sauna), Response.class);
        conversation.end();
        return "home";
    }
}

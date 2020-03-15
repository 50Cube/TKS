package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RestRentableService;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named(value = "restUpdateRentableController")
@RequestScoped
public class RestUpdateRentableController {
    
    @Inject
    DataHolder dh;
    @Inject
    private RestRentableService rentableService;
    
    private HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(request.getRequestURL()
           .substring(0, (request.getRequestURL().length() - request.getServletPath().length())).concat("/resources/model"));
    
    private String roomNumber;
    private String saunaNumber;
    private String area;
    private String beds;
    
    private String price;
    
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String number) {
        this.roomNumber = number;
    }
    
     public String getSaunaNumber() {
        return saunaNumber;
    }

    public void setSaunaNumber(String number) {
        this.saunaNumber = number;
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

    public RestUpdateRentableController() {
    }
 
    @PostConstruct
    private void init()
    {
        roomNumber = Integer.toString(dh.getRoom().getNumber());
        saunaNumber = Integer.toString(dh.getSauna().getNumber());
        area = Double.toString(dh.getRoom().getArea());
        beds = Integer.toString(dh.getRoom().getBeds());
        price = Double.toString(dh.getSauna().getPricePerHour());
    }
    
    public String updateRoom() {
        if(!rentableService.checkIfExists(Integer.parseInt(this.roomNumber)))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot update this Room, it was earlier deleted"));
        webTarget.path("room/{number}").resolveTemplate("number", Integer.parseInt(this.roomNumber)).request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Room(Integer.parseInt(this.roomNumber), Double.parseDouble(this.area), Integer.parseInt(this.beds))), Response.class);
        return "RestListRentables";
    }
    
    public String updateSauna() {
        if(!rentableService.checkIfExists(Integer.parseInt(this.saunaNumber)))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot update this Sauna, it was earlier deleted"));
        webTarget.path("sauna/{number}").resolveTemplate("number", Integer.parseInt(this.saunaNumber)).request(MediaType.APPLICATION_JSON)
                .put(Entity.json(new Sauna(Integer.parseInt(this.saunaNumber), Double.parseDouble(this.price))), Response.class);
        return "RestListRentables";
    }
}

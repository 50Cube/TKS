package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RestRentableService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@Named(value = "restListRentablesController")
@ViewScoped
public class RestListRentablesController implements Serializable {
    
    @Inject
    private DataHolder dh;
    @Inject
    private RestRentableService rentableService;
    
    private Map<Integer, Rentable> rentables;
    private Map<Integer, Room> rooms;
    private Map<Integer, Sauna> saunas;
    private String filter;
    
    private HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(request.getRequestURL()
           .substring(0, (request.getRequestURL().length() - request.getServletPath().length())).concat("/resources/model"));
    
    public RestListRentablesController() {
    }
    
    @PostConstruct
    public void loadRentables()
    {
        rooms = webTarget.path("rooms").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, Room>>() {});
        saunas = webTarget.path("saunas").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, Sauna>>() {});
    }
    
    public Map<Integer, Rentable> getRentables()
    {
        return rentables;
    }
    
    public Map<Integer, Room> getRooms()
    {
        return rooms;
    }
    
    public Map<Integer, Sauna> getSaunas()
    {
        return saunas;
    }
    
    public void deleteRentable(int number)
    {
        if(!this.rentableService.checkIfIsNotRented(number))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot delete rented room or sauna"));
        webTarget.path("rentable/{number}").resolveTemplate("number", number).request().delete();
        loadRentables();
    }
    
    public String saveRoom(Room room) {
        dh.setRoom(room);
        dh.setSauna(new Sauna(0,0));
        return "RestUpdateRoom.xhtml";
    }
    
    public String saveSauna(Sauna sauna) {
        dh.setSauna(sauna);
        dh.setRoom(new Room(0,0,0));
        return "RestUpdateSauna.xhtml";
    }
    
    public void getFilteredRentables() {
        rooms = webTarget.path("rooms/{filter}").resolveTemplate("filter", this.filter).request(MediaType.APPLICATION_JSON)
                  .get(new GenericType<Map<Integer, Room>>() {});
        saunas = webTarget.path("saunas/{filter}").resolveTemplate("filter", this.filter).request(MediaType.APPLICATION_JSON)
                .get(new GenericType<Map<Integer, Sauna>>() {});
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
}

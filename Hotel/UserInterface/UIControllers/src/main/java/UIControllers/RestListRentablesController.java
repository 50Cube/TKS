package UIControllers;

import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
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
    
    private Map<Integer, RentableUI> rentables;
    private Map<Integer, RoomUI> rooms;
    private Map<Integer, SaunaUI> saunas;
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
        rooms = webTarget.path("rooms").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, RoomUI>>() {});
        saunas = webTarget.path("saunas").request(MediaType.APPLICATION_JSON).get(new GenericType<Map<Integer, SaunaUI>>() {});
    }
    
    public Map<Integer, RentableUI> getRentables()
    {
        return rentables;
    }
    
    public Map<Integer, RoomUI> getRooms()
    {
        return rooms;
    }
    
    public Map<Integer, SaunaUI> getSaunas()
    {
        return saunas;
    }
    
    public void deleteRentable(int number)
    {
        webTarget.path("rentable/{number}").resolveTemplate("number", number).request().delete();
        loadRentables();
    }
    
    public String saveRoom(RoomUI room) {
        dh.setRoom(room);
        dh.setSauna(new SaunaUI(0,0));
        return "RestUpdateRoom.xhtml";
    }
    
    public String saveSauna(SaunaUI sauna) {
        dh.setSauna(sauna);
        dh.setRoom(new RoomUI(0,0,0));
        return "RestUpdateSauna.xhtml";
    }
    
    public void getFilteredRentables() {
        rooms = webTarget.path("rooms/{filter}").resolveTemplate("filter", this.filter).request(MediaType.APPLICATION_JSON)
                  .get(new GenericType<Map<Integer, RoomUI>>() {});
        saunas = webTarget.path("saunas/{filter}").resolveTemplate("filter", this.filter).request(MediaType.APPLICATION_JSON)
                .get(new GenericType<Map<Integer, SaunaUI>>() {});
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
}

package UIControllers;


import com.mycompany.store.Services.RentService;
import pl.lodz.p.it.UIPorts.Aggregates.RentAdapterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;
import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.UserUI;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listRentsController")
@ViewScoped
public class listRentsController implements Serializable {

    @Inject
    private RentAdapterUI rentService;
    
    @Inject
    private DataHolder dh;

    private RentableUI rentable;
    private UserUI user;
    private String filterPast;
    private String filterCurrent;
    
    private Map<UUID, RentUI> pastRents;
    private Map<UUID, RentUI> currentRents;
    private Map<UUID, RentUI> rentsForClient;
    private Map<UUID, RentUI> rentsForRentable;
    
    public listRentsController() {
    }
    
    @PostConstruct
    public void loadRents() {
        pastRents = rentService.getPastRents();
        currentRents = rentService.getCurrentRents();
    }
    
    public Map<UUID, RentUI> getPastRents() {
        return pastRents;
    }
    
    public Map<UUID, RentUI> getCurrentRents() {
        return currentRents;
    }
    
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
        loadRents();
    }

    public String getRentsForRentablePrepare(RentableUI rentable) {
        dh.setRentable(rentable);
        return "listRentsForRentable.xhtml";
    }

    public Map<UUID, RentUI> getRentsForRentable() {
        this.rentable = dh.getRentable();
        rentsForRentable = rentService.getRentsForRentable(rentable);
        return rentsForRentable;
    }
    
    public RentableUI getRentable() {
        this.rentable = dh.getRentable();
        return this.rentable;
    }
    
    public String getRentsForClientPrepare(UserUI user) {
        dh.setUser(user);
        return "listRentsForClient.xhtml";
    }
    
    public Map<UUID, RentUI> getRentsForClient() {
        this.user = dh.getUser();
        rentsForClient = rentService.getRentsForClient(user);
        return rentsForClient;
    }

    public UserUI getUser() {
        this.user = dh.getUser();
        return this.user;
    }
    
    public void getFilteredPastRents() {
        pastRents = rentService.getFilteredPastRents(this.filterPast);
    }
    
    public void getFilteredCurrentRents() {
        currentRents = rentService.getFilteredCurrentRents(this.filterCurrent);
    }
    
    public String getFilterPast() {
        return this.filterPast;
    }
    
    public void setFilterPast(String filter) {
        this.filterPast = filter;
    }
    
    public String getFilterCurrent() {
        return this.filterCurrent;
    }
    
    public void setFilterCurrent(String filter) {
        this.filterCurrent = filter;
    }
}

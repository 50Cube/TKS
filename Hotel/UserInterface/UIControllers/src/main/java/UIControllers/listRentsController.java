package UIControllers;


import com.mycompany.store.Services.RentService;
import pl.lodz.p.it.Converters.UI.RentConverterUI;
import pl.lodz.p.it.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.Converters.UI.UserConverterUI;
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
    private RentService rentService;
    
    @Inject
    private DataHolder dh;

    @Inject
    private RentConverterUI converterUI;

    @Inject
    private RentableConverterUI rentableConverterUI;

    @Inject
    private UserConverterUI userConverterUI;
    
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
        pastRents = converterUI.convertRentMapToUI(rentService.getPastRents());
        currentRents = converterUI.convertRentMapToUI(rentService.getCurrentRents());
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
        rentsForRentable = converterUI.convertRentMapToUI(rentService.getRentsForRentable(rentableConverterUI.convertToDomain(rentable)));
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
        rentsForClient = converterUI.convertRentMapToUI(rentService.getRentsForClient(userConverterUI.convertToDomain(user)));
        return rentsForClient;
    }

    public UserUI getUser() {
        this.user = dh.getUser();
        return this.user;
    }
    
    public void getFilteredPastRents() {
        pastRents = converterUI.convertRentMapToUI(rentService.getFilteredPastRents(this.filterPast));
    }
    
    public void getFilteredCurrentRents() {
        currentRents = converterUI.convertRentMapToUI(rentService.getFilteredCurrentRents(this.filterCurrent));
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

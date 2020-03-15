package com.mycompany.store.Controllers;

import com.mycompany.store.Model.User;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Services.RentService;
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
    
    private Rentable rentable;
    private User user;
    private String filterPast;
    private String filterCurrent;
    
    private Map<UUID, Rent> pastRents;
    private Map<UUID, Rent> currentRents;
    private Map<UUID, Rent> rentsForClient;
    private Map<UUID, Rent> rentsForRentable;
    
    public listRentsController() {
    }
    
    @PostConstruct
    public void loadRents() {
        pastRents = rentService.getPastRents();
        currentRents = rentService.getCurrentRents();
    }
    
    public Map<UUID, Rent> getPastRents() {
        return pastRents;
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        return currentRents;
    }
    
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
        loadRents();
    }
    
    public String getRentsForRentablePrepare(Rentable rentable) {
        dh.setRentable(rentable);
        return "listRentsForRentable.xhtml";
    }

    public Map<UUID, Rent> getRentsForRentable() {
        this.rentable = dh.getRentable();
        rentsForRentable = rentService.getRentsForRentable(rentable);
        return rentsForRentable;
    }
    
    public Rentable getRentable() {
        this.rentable = dh.getRentable();
        return this.rentable;
    }
    
    public String getRentsForClientPrepare(User user) {
        dh.setUser(user);
        return "listRentsForClient.xhtml";
    }
    
    public Map<UUID, Rent> getRentsForClient() {
        this.user = dh.getUser();
        rentsForClient = rentService.getRentsForClient(user);
        return rentsForClient;
    }

    public User getUser() {
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

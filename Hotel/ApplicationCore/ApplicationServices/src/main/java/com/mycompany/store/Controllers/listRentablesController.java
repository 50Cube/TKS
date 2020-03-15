package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RentableService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "listRentablesController")
@ViewScoped
public class listRentablesController implements Serializable{

    @Inject
    private RentableService rentableService;
    
    @Inject
    private DataHolder dh;
    
    private Map<Integer, Rentable> rentables;
    private Map<Integer, Room> rooms;
    private Map<Integer, Sauna> saunas;
    private String filter;
    
    public listRentablesController() {
    }
    
    @PostConstruct
    public void loadRentables()
    {
        rentables = rentableService.getRentables();
        rooms = rentableService.getRooms();
        saunas = rentableService.getSaunas();
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
    
    public void deleteRentable(int number) throws Exception
    {
        rentableService.deleteRentable(number);
        loadRentables();
    }
    
    public String saveRoom(Room room) {
        dh.setRoom(room);
        dh.setSauna(new Sauna(0,0));
        return "updateRoom.xhtml";
    }
    
    public String saveSauna(Sauna sauna) {
        dh.setSauna(sauna);
        dh.setRoom(new Room(0,0,0));
        return "updateSauna.xhtml";
    }
    
    public void getFilteredRentables() {
        rentables = rentableService.getFilteredRentables(this.filter);
        rooms = rentableService.getFilteredRooms(this.filter);
        saunas = rentableService.getFilteredSaunas(this.filter);
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
}

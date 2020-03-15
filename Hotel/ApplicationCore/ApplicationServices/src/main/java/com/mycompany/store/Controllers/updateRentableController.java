package com.mycompany.store.Controllers;

import com.mycompany.store.Services.RentableService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "updateRentableController")
@RequestScoped
public class updateRentableController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private RentableService rentableService;
    
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
    
    public updateRentableController() {
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
        rentableService.updateRoom(Integer.parseInt(roomNumber), Double.parseDouble(area), Integer.parseInt(beds));
        return "listRentables";
    }
    
    public String updateSauna() {
        rentableService.updateSauna(Integer.parseInt(saunaNumber), Double.parseDouble(price));
        return "listRentables";
    }
}

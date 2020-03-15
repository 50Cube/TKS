package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Services.RentableService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "addRentableController")
@ConversationScoped
public class addRentableController implements Serializable{

    @Inject
    private RentableService rentableService;
    
    @Inject
    private Conversation conversation;
    
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
    
    
    public addRentableController() {
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
        return "addRoom";
    }
    
    public String addRoomConfirm() {
    rentableService.addRentable(room);
        conversation.end();
        return "home";
    }
    
    public String addSauna() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        sauna.setNumber(Integer.parseInt(number));
        sauna.setPricePerHour(Double.parseDouble(price));
        return "addSauna";
    }
    
    public String addSaunaConfirm() {
        rentableService.addRentable(sauna);
        conversation.end();
        return "home";
    }
}

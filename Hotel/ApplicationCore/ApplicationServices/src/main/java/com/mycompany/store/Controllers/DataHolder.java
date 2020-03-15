package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Model.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "dataHolder")
@SessionScoped
public class DataHolder implements Serializable {

    private Rentable rentable;
    private Room room;
    private Sauna sauna;
    private User user;
    
    public Rentable getRentable() {
        return this.rentable;
    }
    
    public void setRentable(Rentable rentable) {
        this.rentable = rentable;
    }
    
    
    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

    
    public Sauna getSauna() {
        return this.sauna;
    }
    
    public void setSauna(Sauna sauna) {
        this.sauna = sauna;
    }

 
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}

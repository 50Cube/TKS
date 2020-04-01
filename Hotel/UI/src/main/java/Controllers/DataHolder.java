package Controllers;

import Model.RentableUI;
import Model.RoomUI;
import Model.SaunaUI;
import Model.UserUI;
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

    private RentableUI rentable;
    private RoomUI room;
    private SaunaUI sauna;
    private UserUI user;
    
    public RentableUI getRentable() {
        return this.rentable;
    }
    
    public void setRentable(RentableUI rentable) {
        this.rentable = rentable;
    }
    
    
    public RoomUI getRoom() {
        return this.room;
    }
    
    public void setRoom(RoomUI room) {
        this.room = room;
    }

    
    public SaunaUI getSauna() {
        return this.sauna;
    }
    
    public void setSauna(SaunaUI sauna) {
        this.sauna = sauna;
    }

 
    public UserUI getUser() {
        return this.user;
    }
    
    public void setUser(UserUI user) {
        this.user = user;
    }
}

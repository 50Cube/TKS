package Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Data
public class RentUI {

    private UUID id = UUID.randomUUID();

    private RentableUI rentable;

    private ClientUI client;

    private Calendar rentStart;

    private Calendar rentStop;
    
    public RentUI() {}
    
    public RentUI(RentableUI rentable, ClientUI client, Calendar start, Calendar stop) {
        this.rentable = rentable;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }

    public RentUI(UUID id,  RentableUI rentable,  ClientUI client,  Calendar rentStart, Calendar rentStop) {
        this.id = id;
        this.rentable = rentable;
        this.client = client;
        this.rentStart = rentStart;
        this.rentStop = rentStop;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String tmp;
        if(this.rentable instanceof RoomUI) tmp = "Room";
        else tmp = "Sauna";
        return tmp + " no. " + this.rentable.getNumber() + " is rent by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.rentStart.getTime()) + " to " + sdf.format(this.rentStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + this.rentable.getNumber();
    }
    
    public String getRentableType() {
        if(this.rentable instanceof RoomUI) return "room";
        else return "sauna";
    }
}

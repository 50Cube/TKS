package pl.lodz.p.it.RestModel;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Data
public class RentRest {

    private UUID id = UUID.randomUUID();

    private RentableRest rentable;

    private ClientRest client;

    private Calendar rentStart;

    private Calendar rentStop;
    
    public RentRest() {}
    
    public RentRest(RentableRest rentable, ClientRest client, Calendar start, Calendar stop) {
        this.rentable = rentable;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }

    public RentRest(UUID id, RentableRest rentable, ClientRest client, Calendar rentStart, Calendar rentStop) {
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
        if(this.rentable instanceof RoomRest) tmp = "Room";
        else tmp = "Sauna";
        return tmp + " no. " + this.rentable.getNumber() + " is rent by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.rentStart.getTime()) + " to " + sdf.format(this.rentStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + this.rentable.getNumber();
    }
    
    public String getRentableType() {
        if(this.rentable instanceof RoomRest) return "room";
        else return "sauna";
    }
}

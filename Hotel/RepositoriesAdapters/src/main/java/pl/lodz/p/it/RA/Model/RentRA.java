package pl.lodz.p.it.RA.Model;

import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Data
public class RentRA {

    private UUID id = UUID.randomUUID();
    private RentableRA rentable;
    private ClientRA client;
    private Calendar rentStart;
    private Calendar rentStop;
    
    public RentRA() {}
    
    public RentRA(RentableRA rentable, ClientRA client, Calendar start, Calendar stop) {
        this.rentable = rentable;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }

    public RentRA(UUID id, RentableRA rentable, ClientRA client, Calendar rentStart, Calendar rentStop) {
        this.id = id;
        this.rentable = rentable;
        this.client = client;
        this.rentStart = rentStart;
        this.rentStop = rentStop;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String tmp = "";
        if(this.rentable instanceof RoomRA) tmp = "Room";
        else tmp = "Sauna";
        return tmp + " no. " + Integer.toString(this.rentable.getNumber()) + " is rent by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.rentStart.getTime()) + " to " + sdf.format(this.rentStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + Integer.toString(this.rentable.getNumber());
    }
    
    public String getRentableType() {
        if(this.rentable instanceof RoomRA) return "room";
        else return "sauna";
    }
}

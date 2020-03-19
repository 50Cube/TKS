package com.mycompany.store.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Data
public class Rent {

    private UUID id = UUID.randomUUID();

    @NotNull
    private Rentable rentable;

    @NotNull
    private Client client;

    @NotNull
    private Calendar rentStart;

    @NotNull
    private Calendar rentStop;
    
    public Rent() {}
    
    public Rent(Rentable rentable, Client client, Calendar start, Calendar stop) {
        this.rentable = rentable;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String tmp;
        if(this.rentable instanceof Room) tmp = "Room";
        else tmp = "Sauna";
        return tmp + " no. " + this.rentable.getNumber() + " is rent by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.rentStart.getTime()) + " to " + sdf.format(this.rentStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + this.rentable.getNumber();
    }
    
    public String getRentableType() {
        if(this.rentable instanceof Room) return "room";
        else return "sauna";
    }
}

package com.mycompany.store.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Rent {

    private UUID id = UUID.randomUUID();
    private Rentable rentable;
    private Client client;
    private Calendar rentStart;
    private Calendar rentStop;
    
    public Rent() {}
    
    public Rent(Rentable rentable, Client client, Calendar start, Calendar stop) {
        this.rentable = rentable;
        this.client = client;
        this.rentStart = start;
        this.rentStop = stop;
    }
    
    public UUID getId()
    {
        return this.id;
    }
    
    public Rentable getRentable()
    {
        return this.rentable;
    }
    
    public void setRentable(Rentable rentable) {
        this.rentable = rentable;
    }
    
    public Client getClient()
    {
        return this.client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Calendar getRentStart()
    {
        return this.rentStart;
    }
    
    public void setRentStart(Calendar date)
    {
        this.rentStart = date;
    }
    
    public Calendar getRentStop()
    {
        return this.rentStop;
    }
    
    public void setRentStop(Calendar date)
    {
        this.rentStop = date;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String tmp = "";
        if(this.rentable instanceof Room) tmp = "Room";
        else tmp = "Sauna";
        return tmp + " no. " + Integer.toString(this.rentable.getNumber()) + " is rent by " + this.client.getName() + " " + this.client.getSurname() + " from " + sdf.format(this.rentStart.getTime()) + " to " + sdf.format(this.rentStop.getTime());
    }
    
    public String toFilterString() {
        return this.id.toString() + " " + this.client.toFilterString() + " " + Integer.toString(this.rentable.getNumber());
    }
    
    public String getRentableType() {
        if(this.rentable instanceof Room) return "room";
        else return "sauna";
    }
}

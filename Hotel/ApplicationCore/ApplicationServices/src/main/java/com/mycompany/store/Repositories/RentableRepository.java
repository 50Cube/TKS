package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named(value = "rentableRepository")
@ApplicationScoped
public class RentableRepository {

    private Map<Integer, Rentable> rentables;
    
    public RentableRepository() {
        rentables = new HashMap<>();
    }
    
    public Map<Integer, Rentable> getRentables()
    {
        return new HashMap<>(rentables);
    }
    
    public Map<Integer, Room> getRooms() {
        Map<Integer, Room> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (rentable instanceof Room)).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), (Room) rentable);
        });
        
        return tmp;
    }
    
    public Map<Integer, Sauna> getSaunas() {
        Map<Integer, Sauna> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (rentable instanceof Sauna)).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), (Sauna) rentable);
        });
        
        return tmp;
    }
    
    public Rentable getRentable(int number)
    { 
        return rentables.get(number);
    }
    
    public synchronized void addRentable(Rentable rentable)
    {
        rentables.put(rentable.getNumber(), rentable);
    }
    
    public synchronized void updateRentable(int number, Rentable rentable)
    {
        rentables.replace(number, rentable);
    }
    
    public synchronized void deleteRentable(Rentable room)
    {
        rentables.remove(room.getNumber());
    }
    
    public Map<Integer, Rentable> getFilteredRentables(String input) {
        Map<Integer, Rentable> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (Integer.toString(rentable.getNumber()).contains(input.trim()))).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), rentable);
        });
        
        return tmp;
    }
    
    public Map<Integer, Room> getFilteredRooms(String input) {
        Map<Integer, Room> tmp = new HashMap<>();
        
        for(Rentable rentable : rentables.values())
            if(rentable instanceof Room && Integer.toString(rentable.getNumber()).contains(input.trim()))
                tmp.put(rentable.getNumber(), (Room) rentable);
        
        return tmp;
    }
    
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        Map<Integer, Sauna> tmp = new HashMap<>();
        
        for(Rentable rentable : rentables.values())
            if(rentable instanceof Sauna && Integer.toString(rentable.getNumber()).contains(input.trim()))
                tmp.put(rentable.getNumber(), (Sauna) rentable);
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataRentable()
    {
        addRentable(new Room(1,50,2));
        addRentable(new Room(2,30,1));
        addRentable(new Room(3,62,3));
        addRentable(new Room(4,78,5));
        addRentable(new Room(5,75,4));
        
        addRentable(new Sauna(10,20));
        addRentable(new Sauna(20,25));
    }
}

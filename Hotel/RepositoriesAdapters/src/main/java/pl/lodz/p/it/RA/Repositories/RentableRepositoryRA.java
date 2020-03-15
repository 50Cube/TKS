package pl.lodz.p.it.RA.Repositories;

import pl.lodz.p.it.RA.Model.RentableRA;
import pl.lodz.p.it.RA.Model.RoomRA;
import pl.lodz.p.it.RA.Model.SaunaRA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;


@Named(value = "rentableRepositoryRA")
@ApplicationScoped
public class RentableRepositoryRA {

    private Map<Integer, RentableRA> rentables;
    
    public RentableRepositoryRA() {
        rentables = new HashMap<>();
    }
    
    public Map<Integer, RentableRA> getRentables()
    {
        return new HashMap<>(rentables);
    }
    
    public Map<Integer, RoomRA> getRooms() {
        Map<Integer, RoomRA> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (rentable instanceof RoomRA)).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), (RoomRA) rentable);
        });
        
        return tmp;
    }
    
    public Map<Integer, SaunaRA> getSaunas() {
        Map<Integer, SaunaRA> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (rentable instanceof SaunaRA)).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), (SaunaRA) rentable);
        });
        
        return tmp;
    }
    
    public RentableRA getRentable(int number)
    { 
        return rentables.get(number);
    }
    
    public synchronized void addRentable(RentableRA rentable)
    {
        rentables.put(rentable.getNumber(), rentable);
    }
    
    public synchronized void updateRentable(int number, RentableRA rentable)
    {
        rentables.replace(number, rentable);
    }
    
    public synchronized void deleteRentable(RentableRA room)
    {
        rentables.remove(room.getNumber());
    }
    
    public Map<Integer, RentableRA> getFilteredRentables(String input) {
        Map<Integer, RentableRA> tmp = new HashMap<>();
        
        rentables.values().stream().filter((rentable) -> (Integer.toString(rentable.getNumber()).contains(input.trim()))).forEachOrdered((rentable) -> {
            tmp.put(rentable.getNumber(), rentable);
        });
        
        return tmp;
    }
    
    public Map<Integer, RoomRA> getFilteredRooms(String input) {
        Map<Integer, RoomRA> tmp = new HashMap<>();
        
        for(RentableRA rentable : rentables.values())
            if(rentable instanceof RoomRA && Integer.toString(rentable.getNumber()).contains(input.trim()))
                tmp.put(rentable.getNumber(), (RoomRA) rentable);
        
        return tmp;
    }
    
    public Map<Integer, SaunaRA> getFilteredSaunas(String input) {
        Map<Integer, SaunaRA> tmp = new HashMap<>();
        
        for(RentableRA rentable : rentables.values())
            if(rentable instanceof SaunaRA && Integer.toString(rentable.getNumber()).contains(input.trim()))
                tmp.put(rentable.getNumber(), (SaunaRA) rentable);
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataRentable()
    {
        addRentable(new RoomRA(1,50,2));
        addRentable(new RoomRA(2,30,1));
        addRentable(new RoomRA(3,62,3));
        addRentable(new RoomRA(4,78,5));
        addRentable(new RoomRA(5,75,4));
        
        addRentable(new SaunaRA(10,20));
        addRentable(new SaunaRA(20,25));
    }
}

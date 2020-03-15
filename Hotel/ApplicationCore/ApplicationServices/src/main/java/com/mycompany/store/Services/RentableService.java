package com.mycompany.store.Services;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Repositories.RentRepository;
import com.mycompany.store.Repositories.RentableRepository;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "rentableService")
@Dependent
public class RentableService implements Serializable{
    
    @Inject
    private RentableRepository rentableRepository;
    
    @Inject
    private RentRepository rentRepository;
    
    public RentableService() {
    }
    
    public Map<Integer, Rentable> getRentables()
    {
        return rentableRepository.getRentables();
    }
    
    public Rentable getRentable(int number)
    { 
        return rentableRepository.getRentable(number);
    }
    
    public Map<Integer, Room> getRooms() {
        return rentableRepository.getRooms();
    }
    
    public Map<Integer, Sauna> getSaunas() {
        return rentableRepository.getSaunas();
    }
 
    public void addRentable(Rentable rentable)
    {
        if(rentableRepository.getRentables().containsKey(rentable.getNumber()))
            throw new IllegalArgumentException("Room or sauna with this number already exists.");
        else rentableRepository.addRentable(rentable);
    }
    
    public void updateRoom(int number, double newArea, int newBeds)
    {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(rentableRepository.getRentable(number) instanceof Room)
                rentableRepository.updateRentable(number, new Room(number, newArea, newBeds));
        }
        else throw new IllegalArgumentException("Room does not exists");
    }
    
    public void updateSauna(int number, double newCost) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(rentableRepository.getRentable(number) instanceof Sauna)
                rentableRepository.updateRentable(number, new Sauna(number, newCost));
        }
        else throw new IllegalArgumentException("Sauna does not exists");
    }
    
    public void deleteRentable(int number)
    {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(checkIfIsRented(number))
                rentableRepository.deleteRentable(rentableRepository.getRentable(number));
            else throw new IllegalArgumentException("Cannot remove room or sauna which is rented");
        }
        else throw new IllegalArgumentException("Room or sauna with that number does not exist");
    }
    
    private boolean checkIfIsRented(int number) {
        return rentRepository.getCurrentRents().values().stream().noneMatch((rent) -> (rent.getRentable().getNumber() == number));
    }
    
    public Map<Integer, Rentable> getFilteredRentables(String input) {
        return rentableRepository.getFilteredRentables(input);
    }
    
    public Map<Integer, Room> getFilteredRooms(String input) {
        return rentableRepository.getFilteredRooms(input);
    }
    
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        return rentableRepository.getFilteredSaunas(input);
    }
}

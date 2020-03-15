package com.mycompany.store.Services;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Repositories.RentRepository;
import com.mycompany.store.Repositories.RentableRepository;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Named(value = "rentService")
@Dependent
public class RentService implements Serializable {

    @Inject
    private RentRepository rentRepository;
    
    @Inject
    private RentableRepository rentableRepository;
    
    public RentService() {
    }
    
    public Map<UUID, Rent> getRents() {
        return rentRepository.getRents();
    }
    
    public Map<UUID, Rent> getPastRents() {
        return rentRepository.getPastRents();
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        return rentRepository.getCurrentRents();
    }

    public Map<UUID, Rent> getRentsForClient(User user) {
        return rentRepository.getRentsForClient(user);
    }
    
    public Map<UUID, Rent> getRentsForRentable(Rentable rentable) {
        return rentRepository.getRentsForRentable(rentable);
    }
    
    public Rent getRent(UUID id) {
        return rentRepository.getRent(id);
    }
    
    public void addRent(Rentable rentable, Client client, Calendar start, Calendar stop) {
        if(rentableRepository.getRentables().containsKey(rentable.getNumber()))
        {
            if(client.getIsActive())
                rentRepository.addRent(new Rent(rentable, client, start, stop));
            else throw new IllegalArgumentException("Client is inactive");
        }
        else throw new IllegalArgumentException("Room or sauna does not exists");
    }
    
    public void deleteRent(UUID id) throws Exception {
        if(rentRepository.getRents().containsKey(id))
             if(rentRepository.getRent(id).getRentStop().before(Calendar.getInstance()))
                rentRepository.deleteRent(rentRepository.getRent(id));
            else throw new Exception("Rent is not finished");
    }
    
    public Map<UUID, Rent> getFilteredPastRents(String input) {
        return rentRepository.getFilteredPastRents(input);
    }
    
    public Map<UUID, Rent> getFilteredCurrentRents(String input) {
        return rentRepository.getFilteredCurrentRents(input);
    }
    
    public boolean checkIfRented(Calendar start, Calendar stop) {
        for(Rent rent : this.getRents().values())
            if( ( start.before(rent.getRentStart()) && stop.after(rent.getRentStop()) ) ||
                ( start.after(rent.getRentStart()) && stop.before(rent.getRentStop()) ) ||
                ( start.before(rent.getRentStart()) && stop.after(rent.getRentStart()) ) ||
                ( start.before(rent.getRentStop()) && stop.after(rent.getRentStop()) ) )
                return false;
        return true;
    }
}

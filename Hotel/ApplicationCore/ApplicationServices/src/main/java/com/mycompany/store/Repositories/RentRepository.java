package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "rentRepository")
@ApplicationScoped
public class RentRepository {

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private RentableRepository rentableRepository;
    
    private Map<UUID, Rent> rents;
    
    public RentRepository() {
        rents = new HashMap<>();
    }
    
    public Map<UUID, Rent> getRents()
    {
        return new HashMap<>(rents);
    }
    
    public Map<UUID, Rent> getPastRents() {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        rents.values().stream().filter((r) -> (r.getRentStop().before(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public Map<UUID, Rent> getCurrentRents() {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        rents.values().stream().filter((r) -> (r.getRentStop().after(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public Rent getRent(UUID id)
    {
        return rents.get(id);
    }
    
    public synchronized void addRent(Rent rent)
    {
        rents.put(rent.getId(), rent);
    }
    
    public Map<UUID, Rent> getRentsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRentStart().after(startDate) && rent.getRentStop().before(stopDate))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, Rent> getRentsForClient(User user)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getClient().getLogin().equals(user.getLogin()))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, Rent> getRentsForRentable(Rentable rentable)
    {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        for (Rent rent : rents.values()) {
            if(rent.getRentable().getNumber() == rentable.getNumber())
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public synchronized void deleteRent(Rent rent)
    {
        rents.remove(rent.getId());
    }
    
    public Map<UUID, Rent> getFilteredPastRents(String input) {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        this.getPastRents().values().stream().filter((rent) -> (rent.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((rent) -> {
            tmp.put(rent.getId(), rent);
        });
        
        return tmp;
    }
    
    public Map<UUID, Rent> getFilteredCurrentRents(String input) {
        Map<UUID, Rent> tmp = new HashMap<>();
        
        this.getCurrentRents().values().stream().filter((rent) -> (rent.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((rent) -> {
            tmp.put(rent.getId(), rent);
        });
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataRent() {
        addRent(new Rent(rentableRepository.getRentable(1), (Client) userRepository.getUser("client1"), new GregorianCalendar(2019,12,05), new GregorianCalendar(2020,02,28)));
        addRent(new Rent(rentableRepository.getRentable(2), (Client) userRepository.getUser("client1"), new GregorianCalendar(2019,07,15), new GregorianCalendar(2019,07,25)));
        addRent(new Rent(rentableRepository.getRentable(2), (Client) userRepository.getUser("client1"), new GregorianCalendar(2019,05,20), new GregorianCalendar(2019,06,01)));
    
        addRent(new Rent(rentableRepository.getRentable(10), (Client) userRepository.getUser("client2"), new GregorianCalendar(2019,12,06), new GregorianCalendar(2019,12,24)));
        addRent(new Rent(rentableRepository.getRentable(10), (Client) userRepository.getUser("client2"), new GregorianCalendar(2019,07,15), new GregorianCalendar(2019,07,16)));
        addRent(new Rent(rentableRepository.getRentable(20), (Client) userRepository.getUser("client1"), new GregorianCalendar(2019,01,15), new GregorianCalendar(2019,01,16)));
    }
}

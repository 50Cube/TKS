package pl.lodz.p.it.RA.Repositories;

import pl.lodz.p.it.RA.Model.ClientRA;
import pl.lodz.p.it.RA.Model.RentRA;
import pl.lodz.p.it.RA.Model.RentableRA;
import pl.lodz.p.it.RA.Model.UserRA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;


@Named(value = "rentRepositoryRA")
@ApplicationScoped
public class RentRepositoryRA {

    @Inject
    private UserRepositoryRA userRepository;
    
    @Inject
    private RentableRepositoryRA rentableRepository;
    
    private Map<UUID, RentRA> rents;
    
    public RentRepositoryRA() {
        rents = new HashMap<>();
    }
    
    public Map<UUID, RentRA> getRents()
    {
        return new HashMap<>(rents);
    }
    
    public Map<UUID, RentRA> getPastRents() {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        rents.values().stream().filter((r) -> (r.getRentStop().before(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public Map<UUID, RentRA> getCurrentRents() {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        rents.values().stream().filter((r) -> (r.getRentStop().after(Calendar.getInstance()))).forEachOrdered((r) -> {
            tmp.put(r.getId(), r);
        });
        
        return tmp;
    }
    
    public RentRA getRent(UUID id)
    {
        return rents.get(id);
    }
    
    public synchronized void addRent(RentRA rent)
    {
        rents.put(rent.getId(), rent);
    }
    
    public Map<UUID, RentRA> getRentsBetween(Calendar startDate, Calendar stopDate)
    {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        for (RentRA rent : rents.values()) {
            if(rent.getRentStart().after(startDate) && rent.getRentStop().before(stopDate))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, RentRA> getRentsForClient(UserRA user)
    {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        for (RentRA rent : rents.values()) {
            if(rent.getClient().getLogin().equals(user.getLogin()))
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public Map<UUID, RentRA> getRentsForRentable(RentableRA rentable)
    {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        for (RentRA rent : rents.values()) {
            if(rent.getRentable().getNumber() == rentable.getNumber())
                tmp.put(rent.getId(), rent);
        }
        
        return tmp;
    }
    
    public synchronized void deleteRent(RentRA rent)
    {
        rents.remove(rent.getId());
    }
    
    public Map<UUID, RentRA> getFilteredPastRents(String input) {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        this.getPastRents().values().stream().filter((rent) -> (rent.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((rent) -> {
            tmp.put(rent.getId(), rent);
        });
        
        return tmp;
    }
    
    public Map<UUID, RentRA> getFilteredCurrentRents(String input) {
        Map<UUID, RentRA> tmp = new HashMap<>();
        
        this.getCurrentRents().values().stream().filter((rent) -> (rent.toFilterString().toLowerCase().contains(input.trim())))
                .forEachOrdered((rent) -> {
            tmp.put(rent.getId(), rent);
        });
        
        return tmp;
    }
    
    @PostConstruct
    private void initDataRent() {
        addRent(new RentRA(rentableRepository.getRentable(1), (ClientRA) userRepository.getUser("client1"), new GregorianCalendar(2019,12,05), new GregorianCalendar(2020,02,28)));
        addRent(new RentRA(rentableRepository.getRentable(2), (ClientRA) userRepository.getUser("client1"), new GregorianCalendar(2019,07,15), new GregorianCalendar(2019,07,25)));
        addRent(new RentRA(rentableRepository.getRentable(2), (ClientRA) userRepository.getUser("client1"), new GregorianCalendar(2019,05,20), new GregorianCalendar(2019,06,01)));
    
        addRent(new RentRA(rentableRepository.getRentable(10), (ClientRA) userRepository.getUser("client2"), new GregorianCalendar(2019,12,06), new GregorianCalendar(2019,12,24)));
        addRent(new RentRA(rentableRepository.getRentable(10), (ClientRA) userRepository.getUser("client2"), new GregorianCalendar(2019,07,15), new GregorianCalendar(2019,07,16)));
        addRent(new RentRA(rentableRepository.getRentable(20), (ClientRA) userRepository.getUser("client1"), new GregorianCalendar(2019,01,15), new GregorianCalendar(2019,01,16)));
    }
}

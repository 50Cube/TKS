package pl.lodz.p.it.Aggregates;

import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.User;
import pl.lodz.p.it.Converters.Repository.RentConverterRepository;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.GetFilteredRentsPort;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.GetRentPort;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.GetRentsForResourcePort;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.GetRentsPort;
import pl.lodz.p.it.RA.Model.RentRA;
import pl.lodz.p.it.RA.Repositories.RentRepositoryRA;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.AddRentPort;
import pl.lodz.p.it.InfrastructurePorts.RentPorts.DeleteRentPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Named(value = "rentAdapter")
@ApplicationScoped
public class RentAdapter implements GetRentPort, GetFilteredRentsPort, GetRentsForResourcePort, GetRentsPort , AddRentPort, DeleteRentPort {
    @Inject
    RentConverterRepository converter;
    @Inject
    RentRepositoryRA repositoryRA;

    @Override
    public Map<UUID, Rent> getFilteredPastRents(String input) {
        Map<UUID, RentRA> rents = repositoryRA.getFilteredPastRents(input);
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            Rent rent = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rent);
        }
        return result;
    }

    @Override
    public Map<UUID, Rent> getFilteredCurrentRents(String input) {
        Map<UUID, RentRA> rents = repositoryRA.getFilteredCurrentRents(input);
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            Rent rent = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rent);
        }
        return result;
    }

    @Override
    public Rent getRent(UUID id) {
        return converter.convertToDomain(repositoryRA.getRent(id));
    }

    @Override
    public Map<UUID, Rent> getRentsForClient(User user) {
        Map<UUID, RentRA> rents = repositoryRA.getRents();
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            if(entry.getValue().getClient().getLogin().equals(user.getLogin())){
                Rent rent = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(),rent);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, Rent> getRentsForRentable(Rentable rentable) {
        Map<UUID, RentRA> rents = repositoryRA.getRents();
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            if(entry.getValue().getRentable().getNumber() == rentable.getNumber()){
                Rent rent = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(),rent);
            }
        }
        return result;
    }

    @Override
    public Map<UUID, Rent> getRents() {
        Map<UUID, RentRA> rents = repositoryRA.getRents();
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            Rent rent = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rent);
        }
        return result;
    }

    @Override
    public Map<UUID, Rent> getPastRents() {
        Map<UUID, RentRA> rents = repositoryRA.getPastRents();
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
                Rent rent = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(),rent);
        }
        return result;
    }

    @Override
    public Map<UUID, Rent> getCurrentRents() {
        Map<UUID, RentRA> rents = repositoryRA.getCurrentRents();
        HashMap<UUID,Rent> result = new HashMap<>();
        for(Map.Entry<UUID,RentRA> entry : rents.entrySet()){
            Rent rent = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rent);
        }
        return result;
    }

    @Override
    public void addRent(Rent rent) {
        RentRA rentRA = converter.convertToRepository(rent);
        repositoryRA.addRent(rentRA);
    }

    @Override
    public void deleteRent(Rent rent) {
        repositoryRA.deleteRent(converter.convertToRepository(rent));
    }
}

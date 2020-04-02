package pl.lodz.p.it.Aggregates;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import pl.lodz.p.it.Converters.Repository.RentableConverterRepository;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.GetFilteredRentablesPort;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.GetRentablePort;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.GetRentablesPort;
import pl.lodz.p.it.RA.Model.RentableRA;
import pl.lodz.p.it.RA.Model.RoomRA;
import pl.lodz.p.it.RA.Model.SaunaRA;
import pl.lodz.p.it.RA.Repositories.RentableRepositoryRA;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.AddRentablePort;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.DeleteRentablePort;
import pl.lodz.p.it.InfrastructurePorts.RentablePorts.UpdateRentablePort;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named(value = "rentableAdapter")
@Dependent
public class RentableAdapter implements GetRentablePort, GetRentablesPort, GetFilteredRentablesPort, AddRentablePort, DeleteRentablePort, UpdateRentablePort {
    @Inject
    RentableConverterRepository converter;
    @Inject
    RentableRepositoryRA repositoryRA;

    @Override
    public Map<Integer, Rentable> getFilteredRentables(String input) {
        HashMap<Integer, Rentable> result = new HashMap<>();
        Map<Integer, RentableRA> filteredRentables = repositoryRA.getFilteredRentables(input);
        for(Map.Entry<Integer,RentableRA> entry : filteredRentables.entrySet()){
            Rentable rentable = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rentable);
        }
        return result;
    }

    @Override
    public Map<Integer, Room> getFilteredRooms(String input) {
        HashMap<Integer, Room> result = new HashMap<>();
        Map<Integer, RentableRA> filteredRentables = repositoryRA.getFilteredRentables(input);
        for(Map.Entry<Integer,RentableRA> entry : filteredRentables.entrySet()){
            if(entry.getValue() instanceof RoomRA){
                Rentable rentable = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(), (Room) rentable);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Sauna> getFilteredSaunas(String input) {
        HashMap<Integer, Sauna> result = new HashMap<>();
        Map<Integer, RentableRA> filteredRentables = repositoryRA.getFilteredRentables(input);
        for(Map.Entry<Integer,RentableRA> entry : filteredRentables.entrySet()){
            if(entry.getValue() instanceof SaunaRA){
                Rentable rentable = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(), (Sauna) rentable);
            }
        }
        return result;
    }

    @Override
    public Rentable getRentable(int number) {
        return converter.convertToDomain(repositoryRA.getRentable(number));
    }

    @Override
    public Map<Integer, Rentable> getRentables() {
        Map<Integer, RentableRA> rentables = repositoryRA.getRentables();
        HashMap<Integer, Rentable> result = new HashMap<>();
        for(Map.Entry<Integer,RentableRA> entry: rentables.entrySet()){
            Rentable rentable = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),rentable);
        }
        return result;
    }

    @Override
    public Map<Integer, Room> getRooms() {
        Map<Integer, RentableRA> rentables = repositoryRA.getRentables();
        HashMap<Integer, Room> result = new HashMap<>();
        for(Map.Entry<Integer,RentableRA> entry: rentables.entrySet()){
            if(entry.getValue() instanceof RoomRA){
                Rentable rentable = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(), (Room) rentable);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Sauna> getSaunas() {
        Map<Integer, RentableRA> rentables = repositoryRA.getRentables();
        HashMap<Integer, Sauna> result = new HashMap<>();
        for(Map.Entry<Integer,RentableRA> entry: rentables.entrySet()){
            if(entry.getValue() instanceof SaunaRA){
                Rentable rentable = converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(), (Sauna) rentable);
            }
        }
        return result;
    }

    @Override
    public void addRentable(Rentable rentable) {
        repositoryRA.addRentable(
                converter.convertToRepository(rentable)
        );
    }

    @Override
    public void deleteRentable(Rentable rentable) {
        repositoryRA.deleteRentable(
                converter.convertToRepository(rentable)
        );
    }

    @Override
    public void updateRentable(int number,Rentable rentable) {
        repositoryRA.updateRentable(number,converter.convertToRepository(rentable));
    }
}

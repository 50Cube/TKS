package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentablePorts;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;

import java.util.Map;

public interface GetFilteredRentablesPort {
    Map<Integer, Rentable> getFilteredRentables(String input);
    Map<Integer, Room> getFilteredRooms(String input);
    Map<Integer, Sauna> getFilteredSaunas(String input);
}

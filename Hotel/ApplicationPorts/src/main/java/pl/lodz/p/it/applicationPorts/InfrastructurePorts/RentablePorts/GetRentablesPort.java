package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentablePorts;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;

import java.util.Map;

public interface GetRentablesPort {
    Map<Integer, Rentable> getRentables();
    Map<Integer, Room> getRooms();
    Map<Integer, Sauna> getSaunas();
}

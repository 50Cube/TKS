package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentPorts;

import com.mycompany.store.Model.Rent;
import java.util.Map;
import java.util.UUID;

public interface GetRentsPort {
    Map<UUID, Rent> getRents();
    Map<UUID, Rent> getPastRents();
    Map<UUID, Rent> getCurrentRents();
}

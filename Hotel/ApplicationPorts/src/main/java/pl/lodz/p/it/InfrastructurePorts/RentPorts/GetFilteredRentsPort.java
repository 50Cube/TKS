package pl.lodz.p.it.InfrastructurePorts.RentPorts;

import com.mycompany.store.Model.Rent;
import java.util.Map;
import java.util.UUID;

public interface GetFilteredRentsPort {
    Map<UUID, Rent> getFilteredPastRents(String input);
    Map<UUID, Rent> getFilteredCurrentRents(String input);
}

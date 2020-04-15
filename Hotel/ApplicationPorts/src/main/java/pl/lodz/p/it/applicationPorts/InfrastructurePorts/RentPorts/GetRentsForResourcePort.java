package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentPorts;

import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.User;
import java.util.Map;
import java.util.UUID;

public interface GetRentsForResourcePort {
    Map<UUID, Rent> getRentsForClient(User user);
    Map<UUID, Rent> getRentsForRentable(Rentable rentable);
}

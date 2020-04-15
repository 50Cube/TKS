package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentPorts;

import com.mycompany.store.Model.Rent;
import java.util.UUID;

public interface GetRentPort {
    Rent getRent(UUID id);
}

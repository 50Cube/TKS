package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentablePorts;

import com.mycompany.store.Model.Rentable;

public interface DeleteRentablePort {
    void deleteRentable(Rentable rentable);
}

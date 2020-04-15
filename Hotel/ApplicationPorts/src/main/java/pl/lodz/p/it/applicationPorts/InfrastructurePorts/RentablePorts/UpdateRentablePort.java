package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentablePorts;

import com.mycompany.store.Model.Rentable;

public interface UpdateRentablePort {
    void updateRentable(int number,Rentable rentable);
}

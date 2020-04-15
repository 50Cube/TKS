package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentablePorts;

import com.mycompany.store.Model.Rentable;

public interface GetRentablePort {
    Rentable getRentable(int number);
}

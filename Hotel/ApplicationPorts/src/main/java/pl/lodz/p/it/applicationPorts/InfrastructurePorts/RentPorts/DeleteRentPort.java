package pl.lodz.p.it.applicationPorts.InfrastructurePorts.RentPorts;

import com.mycompany.store.Model.Rent;

public interface DeleteRentPort {
    void deleteRent(Rent rent);
}

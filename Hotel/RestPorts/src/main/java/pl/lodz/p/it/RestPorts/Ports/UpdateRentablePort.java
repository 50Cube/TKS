package pl.lodz.p.it.RestPorts.Ports;

import pl.lodz.p.it.RestModel.RentableRest;

public interface UpdateRentablePort {
    void updateRentable(int number, RentableRest rentable);
}

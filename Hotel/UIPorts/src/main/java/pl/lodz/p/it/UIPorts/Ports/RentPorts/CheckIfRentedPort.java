package pl.lodz.p.it.UIPorts.Ports.RentPorts;

import java.util.Calendar;

public interface CheckIfRentedPort {
    boolean checkIfRented(Calendar start, Calendar stop);
}

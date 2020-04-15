package pl.lodz.p.it.UIPorts.Ports.RentPorts;

import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.RentableUI;

import java.util.Calendar;

public interface AddRentPort {
    void addRent(RentableUI rentable, ClientUI client, Calendar start, Calendar stop);
}

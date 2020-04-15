package pl.lodz.p.it.UIPorts.Ports.RentPorts;

import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.UserUI;

import java.util.Map;
import java.util.UUID;

public interface GetRentPort {
    Map<UUID, RentUI> getRents();
    Map<UUID, RentUI> getPastRents();
    Map<UUID, RentUI> getCurrentRents();
    Map<UUID, RentUI> getRentsForClient(UserUI user);
    Map<UUID, RentUI> getRentsForRentable(RentableUI rentable);
    Map<UUID, RentUI> getFilteredPastRents(String input);
    Map<UUID, RentUI> getFilteredCurrentRents(String input);
}

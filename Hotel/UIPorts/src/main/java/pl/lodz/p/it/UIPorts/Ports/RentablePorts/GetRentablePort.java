package pl.lodz.p.it.UIPorts.Ports.RentablePorts;

import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;

import java.util.Map;

public interface GetRentablePort {
    Map<Integer, RentableUI> getRentables();
    RentableUI getRentable(int number);
    Map<Integer, RoomUI> getRooms();
    Map<Integer, SaunaUI> getSaunas();
    Map<Integer, RentableUI> getFilteredRentables(String input);
    Map<Integer, RoomUI> getFilteredRooms(String input);
    Map<Integer, SaunaUI> getFilteredSaunas(String input);
}

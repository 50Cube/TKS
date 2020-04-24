package pl.lodz.p.it.RestPorts.Ports;

import pl.lodz.p.it.RestModel.RentableRest;
import pl.lodz.p.it.RestModel.RoomRest;
import pl.lodz.p.it.RestModel.SaunaRest;

import java.util.Map;

public interface GetRentablePort {
    Map<Integer, RentableRest> getRentables();
    RentableRest getRentable(int number);
    Map<Integer, RoomRest> getRooms();
    Map<Integer, SaunaRest> getSaunas();
    Map<Integer, RoomRest> getFilteredRooms(String input);
    Map<Integer, SaunaRest> getFilteredSaunas(String input);
}

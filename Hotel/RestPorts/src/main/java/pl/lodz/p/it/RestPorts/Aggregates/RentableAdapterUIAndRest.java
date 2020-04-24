package pl.lodz.p.it.RestPorts.Aggregates;

import pl.lodz.p.it.RestPorts.Converters.RentableConverterUIAndRest;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.AddRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.DeleteRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.GetRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.UpdateRentablePort;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named(value = "rentableAdapterUIAndRest")
@ApplicationScoped
public class RentableAdapterUIAndRest implements AddRentablePort, DeleteRentablePort, GetRentablePort, UpdateRentablePort {

    @Inject
    RentableAdapterRestAndRA rentableAdapter;

    @Inject
    RentableConverterUIAndRest converter;


    @Override
    public void addRentable(RentableUI rentable) {
        rentableAdapter.addRentable(converter.convertToRest(rentable));
    }

    @Override
    public void deleteRentable(int number) {
        rentableAdapter.deleteRentable(number);
    }

    @Override
    public Map<Integer, RentableUI> getRentables() {
        return converter.convertRentableMapToUI(rentableAdapter.getRentables());
    }

    @Override
    public RentableUI getRentable(int number) {
        return converter.convertToUI(rentableAdapter.getRentable(number));
    }

    @Override
    public Map<Integer, RoomUI> getRooms() {
        return converter.convertRoomMapToUI(rentableAdapter.getRooms());
    }

    @Override
    public Map<Integer, SaunaUI> getSaunas() {
        return converter.convertSaunaMapToUI(rentableAdapter.getSaunas());
    }

    @Override
    public Map<Integer, RentableUI> getFilteredRentables(String input) {
       return null;
    }

    @Override
    public Map<Integer, RoomUI> getFilteredRooms(String input) {
        return converter.convertRoomMapToUI(rentableAdapter.getFilteredRooms(input));
    }

    @Override
    public Map<Integer, SaunaUI> getFilteredSaunas(String input) {
        return converter.convertSaunaMapToUI(rentableAdapter.getFilteredSaunas(input));
    }

    @Override
    public void updateRoom(int number, double newArea, int newBeds) {

    }

    @Override
    public void updateSauna(int number, double newCost) {

    }

    public void updateRentable(int number, RentableUI rentable) {
        rentableAdapter.updateRentable(number, converter.convertToRest(rentable));
    }
}

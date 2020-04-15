package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Services.RentableService;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.AddRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.DeleteRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.GetRentablePort;
import pl.lodz.p.it.UIPorts.Ports.RentablePorts.UpdateRentablePort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named(value = "rentableAdapterUI")
@ApplicationScoped
public class RentableAdapterUI implements AddRentablePort, DeleteRentablePort, GetRentablePort, UpdateRentablePort {
    @Inject
    RentableService rentableService;

    @Inject
    RentableConverterUI converter;
    @Override
    public Map<Integer, RentableUI> getRentables()
    {
        return converter.convertRentableMapToUI(rentableService.getRentables());
    }
    @Override
    public RentableUI getRentable(int number)
    {
        return converter.convertToUI(rentableService.getRentable(number));
    }
    @Override
    public Map<Integer, RoomUI> getRooms() {
        return converter.convertRoomMapToUI(rentableService.getRooms());
    }
    @Override
    public Map<Integer, SaunaUI> getSaunas() {
        return converter.convertSaunaMapToUI(rentableService.getSaunas());
    }
    @Override
    public void addRentable(RentableUI rentable){
        rentableService.addRentable(converter.convertToDomain(rentable));
    }
    @Override
    public void updateRoom(int number, double newArea, int newBeds){
        rentableService.updateRoom(number,newArea,newBeds);
    }
    @Override
    public void updateSauna(int number, double newCost) {
        rentableService.updateSauna(number,newCost);
    }
    @Override
    public void deleteRentable(int number){
        rentableService.deleteRentable(number);
    }
    @Override
    public Map<Integer, RentableUI> getFilteredRentables(String input) {
        return converter.convertRentableMapToUI(rentableService.getFilteredRentables(input));
    }
    @Override
    public Map<Integer, RoomUI> getFilteredRooms(String input)
    {
        return converter.convertRoomMapToUI(rentableService.getFilteredRooms(input));
    }
    @Override
    public Map<Integer, SaunaUI> getFilteredSaunas(String input) {
        return converter.convertSaunaMapToUI(rentableService.getFilteredSaunas(input));
    }
}

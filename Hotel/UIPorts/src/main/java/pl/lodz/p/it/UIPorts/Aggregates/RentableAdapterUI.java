package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Services.RentableService;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named(value = "rentableAdapterUI")
@ApplicationScoped
public class RentableAdapterUI {
    @Inject
    RentableService rentableService;

    @Inject
    RentableConverterUI converter;

    public Map<Integer, RentableUI> getRentables()
    {
        return converter.convertRentableMapToUI(rentableService.getRentables());
    }

    public RentableUI getRentable(int number)
    {
        return converter.convertToUI(rentableService.getRentable(number));
    }

    public Map<Integer, RoomUI> getRooms() {
        return converter.convertRoomMapToUI(rentableService.getRooms());
    }

    public Map<Integer, SaunaUI> getSaunas() {
        return converter.convertSaunaMapToUI(rentableService.getSaunas());
    }

    public void addRentable(RentableUI rentable){
        rentableService.addRentable(converter.convertToDomain(rentable));
    }

    public void updateRoom(int number, double newArea, int newBeds){
        rentableService.updateRoom(number,newArea,newBeds);
    }

    public void updateSauna(int number, double newCost) {
        rentableService.updateSauna(number,newCost);
    }

    public void deleteRentable(int number){
        rentableService.deleteRentable(number);
    }

    public Map<Integer, RentableUI> getFilteredRentables(String input) {
        return converter.convertRentableMapToUI(rentableService.getFilteredRentables(input));
    }

    public Map<Integer, RoomUI> getFilteredRooms(String input)
    {
        return converter.convertRoomMapToUI(rentableService.getFilteredRooms(input));
    }

    public Map<Integer, SaunaUI> getFilteredSaunas(String input) {
        return converter.convertSaunaMapToUI(rentableService.getFilteredSaunas(input));
    }
}

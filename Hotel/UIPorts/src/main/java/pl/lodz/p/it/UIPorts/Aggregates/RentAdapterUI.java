package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Services.RentService;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

@Named(value = "rentAdapterUI")
@ApplicationScoped
public class RentAdapterUI {
    @Inject
    RentService rentService;

    @Inject
    RentConverterUI rentConverter;

    @Inject
    UserConverterUI userConverter;

    @Inject
    RentableConverterUI rentableConverter;

    public Map<UUID, RentUI> getRents() {
        return rentConverter.convertRentMapToUI(rentService.getRents());
    }

    public Map<UUID, RentUI> getPastRents() {
        return rentConverter.convertRentMapToUI(rentService.getPastRents()) ;
    }

    public Map<UUID, RentUI> getCurrentRents() {
        return rentConverter.convertRentMapToUI(rentService.getCurrentRents()) ;
    }

    public Map<UUID, RentUI> getRentsForClient(UserUI user) {
        return rentConverter.convertRentMapToUI(
                rentService.getRentsForClient(
                userConverter.convertToDomain(user)));
    }

    public Map<UUID, RentUI> getRentsForRentable(RentableUI rentable) {
        return rentConverter.convertRentMapToUI(
                rentService.getRentsForRentable(
                rentableConverter.convertToDomain(rentable)));
    }

    public void addRent(RentableUI rentable, ClientUI client, Calendar start, Calendar stop) {
        rentService.addRent(rentableConverter.convertToDomain(rentable),
                            userConverter.convertClientToDomain(client),
                            start, stop);
    }

    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
    }

    public Map<UUID, RentUI> getFilteredPastRents(String input){
        return rentConverter.convertRentMapToUI(rentService.getFilteredPastRents(input));
    }

    public Map<UUID, RentUI> getFilteredCurrentRents(String input){
        return rentConverter.convertRentMapToUI(rentService.getFilteredCurrentRents(input));
    }

    public boolean checkIfRented(Calendar start, Calendar stop) {
       return rentService.checkIfRented(start, stop);
    }
}

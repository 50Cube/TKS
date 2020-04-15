package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Services.RentService;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;
import pl.lodz.p.it.UIPorts.Ports.RentPorts.AddRentPort;
import pl.lodz.p.it.UIPorts.Ports.RentPorts.CheckIfRentedPort;
import pl.lodz.p.it.UIPorts.Ports.RentPorts.DeleteRentPort;
import pl.lodz.p.it.UIPorts.Ports.RentPorts.GetRentPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

@Named(value = "rentAdapterUI")
@ApplicationScoped
public class RentAdapterUI implements AddRentPort, GetRentPort, DeleteRentPort, CheckIfRentedPort {
    @Inject
    RentService rentService;

    @Inject
    RentConverterUI rentConverter;

    @Inject
    UserConverterUI userConverter;

    @Inject
    RentableConverterUI rentableConverter;

    @Override
    public Map<UUID, RentUI> getRents() {
        return rentConverter.convertRentMapToUI(rentService.getRents());
    }
    @Override
    public Map<UUID, RentUI> getPastRents() {
        return rentConverter.convertRentMapToUI(rentService.getPastRents()) ;
    }
    @Override
    public Map<UUID, RentUI> getCurrentRents() {
        return rentConverter.convertRentMapToUI(rentService.getCurrentRents()) ;
    }
    @Override
    public Map<UUID, RentUI> getRentsForClient(UserUI user) {
        return rentConverter.convertRentMapToUI(
                rentService.getRentsForClient(
                userConverter.convertToDomain(user)));
    }
    @Override
    public Map<UUID, RentUI> getRentsForRentable(RentableUI rentable) {
        return rentConverter.convertRentMapToUI(
                rentService.getRentsForRentable(
                rentableConverter.convertToDomain(rentable)));
    }
    @Override
    public void addRent(RentableUI rentable, ClientUI client, Calendar start, Calendar stop) {
        rentService.addRent(rentableConverter.convertToDomain(rentable),
                            userConverter.convertClientToDomain(client),
                            start, stop);
    }
    @Override
    public void deleteRent(UUID id) throws Exception {
        rentService.deleteRent(id);
    }
    @Override
    public Map<UUID, RentUI> getFilteredPastRents(String input){
        return rentConverter.convertRentMapToUI(rentService.getFilteredPastRents(input));
    }
    @Override
    public Map<UUID, RentUI> getFilteredCurrentRents(String input){
        return rentConverter.convertRentMapToUI(rentService.getFilteredCurrentRents(input));
    }
    @Override
    public boolean checkIfRented(Calendar start, Calendar stop) {
       return rentService.checkIfRented(start, stop);
    }
}

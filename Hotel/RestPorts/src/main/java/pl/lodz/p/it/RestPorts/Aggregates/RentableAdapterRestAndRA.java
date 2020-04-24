package pl.lodz.p.it.RestPorts.Aggregates;

import pl.lodz.p.it.RA.Repositories.RentableRepositoryRA;
import pl.lodz.p.it.RestModel.RentableRest;
import pl.lodz.p.it.RestModel.RoomRest;
import pl.lodz.p.it.RestModel.SaunaRest;
import pl.lodz.p.it.RestPorts.Converters.RentableConverterRestAndRA;
import pl.lodz.p.it.RestPorts.Ports.AddRentablePort;
import pl.lodz.p.it.RestPorts.Ports.DeleteRentablePort;
import pl.lodz.p.it.RestPorts.Ports.GetRentablePort;
import pl.lodz.p.it.RestPorts.Ports.UpdateRentablePort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named(value = "rentableAdapterRestAndRA")
@ApplicationScoped
public class RentableAdapterRestAndRA implements AddRentablePort, DeleteRentablePort, GetRentablePort, UpdateRentablePort {

    @Inject
    RentableRepositoryRA rentableRepository;

    @Inject
    RentableConverterRestAndRA converter;

    @Override
    public void addRentable(RentableRest rentable) {
        rentableRepository.addRentable(converter.convertToRA(rentable));
    }

    @Override
    public void deleteRentable(int number) {
        rentableRepository.deleteRentable(rentableRepository.getRentable(number));
    }

    @Override
    public Map<Integer, RentableRest> getRentables() {
        return converter.convertRentableMapToRest(rentableRepository.getRentables());
    }

    @Override
    public RentableRest getRentable(int number) {
        return converter.convertToRest(rentableRepository.getRentable(number));
    }

    @Override
    public Map<Integer, RoomRest> getRooms() {
        return converter.convertRoomMapToRest(rentableRepository.getRooms());
    }

    @Override
    public Map<Integer, SaunaRest> getSaunas() {
        return converter.convertSaunaMapToRest(rentableRepository.getSaunas());
    }

    @Override
    public Map<Integer, RoomRest> getFilteredRooms(String input) {
        return converter.convertRoomMapToRest(rentableRepository.getFilteredRooms(input));
    }

    @Override
    public Map<Integer, SaunaRest> getFilteredSaunas(String input) {
        return converter.convertSaunaMapToRest(rentableRepository.getFilteredSaunas(input));
    }

    @Override
    public void updateRentable(int number, RentableRest rentable) {
        rentableRepository.updateRentable(number, converter.convertToRA(rentable));
    }
}

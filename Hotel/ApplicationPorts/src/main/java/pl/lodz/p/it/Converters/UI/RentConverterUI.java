package pl.lodz.p.it.Converters.UI;

import Model.ClientUI;
import Model.RentUI;
import Model.RentableUI;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;

import javax.inject.Inject;

public class RentConverterUI {
    @Inject
    RentableConverterUI rentableConverter;

    @Inject
    UserConverterUI userConverter;

    public RentUI convertToUI(Rent rent){
        RentableUI rentableUI = rentableConverter.convertToUI(rent.getRentable());
        ClientUI clientUI = (ClientUI) userConverter.convertToUI(rent.getClient());
        return new RentUI(
                rent.getId(),
                rentableUI,
                clientUI,
                rent.getRentStart(),
                rent.getRentStop());
    }

    public Rent convertToDomain(RentUI rent){
        Rentable rentable = rentableConverter.convertToDomain(rent.getRentable());
        Client client = (Client) userConverter.convertToDomain(rent.getClient());
        return new Rent(
                rent.getId(),
                rentable,
                client,
                rent.getRentStart(),
                rent.getRentStop());
    }
}

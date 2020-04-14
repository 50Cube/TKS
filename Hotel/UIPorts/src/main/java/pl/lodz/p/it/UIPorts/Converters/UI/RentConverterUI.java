package pl.lodz.p.it.UIPorts.Converters.UI;


import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Named
@ApplicationScoped
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

    public Map<UUID,RentUI> convertRentMapToUI(Map<UUID,Rent> userMap){
        Map<UUID,RentUI> userUIMap = new HashMap<>();
        for (Map.Entry<UUID,Rent> entry : userMap.entrySet()
        ) {
            RentUI userUI = (RentUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }
}

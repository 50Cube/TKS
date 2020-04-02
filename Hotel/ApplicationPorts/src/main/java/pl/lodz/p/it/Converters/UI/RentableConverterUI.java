package pl.lodz.p.it.Converters.UI;


import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class RentableConverterUI {
    public Rentable convertToDomain(RentableUI rentableUI){
        if(rentableUI instanceof RoomUI){
            return new Room(
                    rentableUI.getNumber(),
                    ((RoomUI) rentableUI).getArea(),
                    ((RoomUI) rentableUI).getBeds()
            );
        }
        if(rentableUI instanceof SaunaUI){
            return new Sauna(
                    rentableUI.getNumber(),
                    ((SaunaUI) rentableUI).getPricePerHour()
            );
        }
        return null;
    }

    public RentableUI convertToUI(Rentable rentable){
        if(rentable instanceof Room){
            return new RoomUI(
                    rentable.getNumber(),
                    ((Room) rentable).getArea(),
                    ((Room) rentable).getBeds()
            );
        }
        if(rentable instanceof Sauna){
            return new SaunaUI(
                    rentable.getNumber(),
                    ((Sauna) rentable).getPricePerHour()
            );
        }
        return null;
    }

    public Map<Integer,RentableUI> convertRentableMapToUI(Map<Integer,Rentable> userMap){
        Map<Integer,RentableUI> userUIMap = new HashMap<>();
        for (Map.Entry<Integer,Rentable> entry : userMap.entrySet()
        ) {
            RentableUI userUI = (RentableUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }

    public Map<Integer,RoomUI> convertRoomMapToUI(Map<Integer,Room> userMap){
        Map<Integer,RoomUI> userUIMap = new HashMap<>();
        for (Map.Entry<Integer,Room> entry : userMap.entrySet()
        ) {
            RoomUI userUI = (RoomUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }

    public Map<Integer,SaunaUI> convertSaunaMapToUI(Map<Integer,Sauna> userMap){
        Map<Integer,SaunaUI> userUIMap = new HashMap<>();
        for (Map.Entry<Integer,Sauna> entry : userMap.entrySet()
        ) {
            SaunaUI userUI = (SaunaUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }
}

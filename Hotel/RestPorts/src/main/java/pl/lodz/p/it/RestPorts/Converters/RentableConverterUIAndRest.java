package pl.lodz.p.it.RestPorts.Converters;

import pl.lodz.p.it.RestModel.RentableRest;
import pl.lodz.p.it.RestModel.RoomRest;
import pl.lodz.p.it.RestModel.SaunaRest;
import pl.lodz.p.it.UIModel.RentableUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
public class RentableConverterUIAndRest {
    public RentableRest convertToRest(RentableUI rentableUI){
        if(rentableUI instanceof RoomUI){
            return new RoomRest(
                    rentableUI.getNumber(),
                    ((RoomUI) rentableUI).getArea(),
                    ((RoomUI) rentableUI).getBeds()
            );
        }
        if(rentableUI instanceof SaunaUI){
            return new SaunaRest(
                    rentableUI.getNumber(),
                    ((SaunaUI) rentableUI).getPricePerHour()
            );
        }
        return null;
    }

    public RentableUI convertToUI(RentableRest rentable){
        if(rentable instanceof RoomRest){
            return new RoomUI(
                    rentable.getNumber(),
                    ((RoomRest) rentable).getArea(),
                    ((RoomRest) rentable).getBeds()
            );
        }
        if(rentable instanceof SaunaRest){
            return new SaunaUI(
                    rentable.getNumber(),
                    ((SaunaRest) rentable).getPricePerHour()
            );
        }
        return null;
    }

    public Map<Integer,RentableUI> convertRentableMapToUI(Map<Integer,RentableRest> map){
        Map<Integer,RentableUI> rentableUIMap = new HashMap<>();
        for (Map.Entry<Integer,RentableRest> entry : map.entrySet()
        ) {
            RentableUI rentableUI = (RentableUI) convertToUI(entry.getValue());
            rentableUIMap.put(entry.getKey(),rentableUI);
        }

        return rentableUIMap;
    }

    public Map<Integer,RoomUI> convertRoomMapToUI(Map<Integer,RoomRest> map){
        Map<Integer,RoomUI> rentableUIMap = new HashMap<>();
        for (Map.Entry<Integer,RoomRest> entry : map.entrySet()
        ) {
            RoomUI rentableUI = (RoomUI) convertToUI(entry.getValue());
            rentableUIMap.put(entry.getKey(),rentableUI);
        }

        return rentableUIMap;
    }

    public Map<Integer,SaunaUI> convertSaunaMapToUI(Map<Integer,SaunaRest> map){
        Map<Integer,SaunaUI> rentableUIMap = new HashMap<>();
        for (Map.Entry<Integer,SaunaRest> entry : map.entrySet()
        ) {
            SaunaUI rentableUI = (SaunaUI) convertToUI(entry.getValue());
            rentableUIMap.put(entry.getKey(),rentableUI);
        }

        return rentableUIMap;
    }
}

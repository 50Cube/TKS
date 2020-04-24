package pl.lodz.p.it.RestPorts.Converters;

import pl.lodz.p.it.RA.Model.RentableRA;
import pl.lodz.p.it.RA.Model.RoomRA;
import pl.lodz.p.it.RA.Model.SaunaRA;
import pl.lodz.p.it.RestModel.RentableRest;
import pl.lodz.p.it.RestModel.RoomRest;
import pl.lodz.p.it.RestModel.SaunaRest;


import java.util.HashMap;
import java.util.Map;

public class RentableConverterRestAndRA {
    public RentableRest convertToRest(RentableRA rentableUI){
        if(rentableUI instanceof RoomRA){
            return new RoomRest(
                    rentableUI.getNumber(),
                    ((RoomRA) rentableUI).getArea(),
                    ((RoomRA) rentableUI).getBeds()
            );
        }
        if(rentableUI instanceof SaunaRA){
            return new SaunaRest(
                    rentableUI.getNumber(),
                    ((SaunaRA) rentableUI).getPricePerHour()
            );
        }
        return null;
    }

    public RentableRA convertToRA(RentableRest rentable){
        if(rentable instanceof RoomRest){
            return new RoomRA(
                    rentable.getNumber(),
                    ((RoomRest) rentable).getArea(),
                    ((RoomRest) rentable).getBeds()
            );
        }
        if(rentable instanceof SaunaRest){
            return new SaunaRA(
                    rentable.getNumber(),
                    ((SaunaRest) rentable).getPricePerHour()
            );
        }
        return null;
    }

    public Map<Integer, RentableRest> convertRentableMapToRest(Map<Integer, RentableRA> map) {
        Map<Integer,RentableRest> rentableUIMap = new HashMap<>();
        for (Map.Entry<Integer,RentableRA> entry : map.entrySet()
        ) {
            RentableRest rentableRest = (RentableRest) convertToRest(entry.getValue());
            rentableUIMap.put(entry.getKey(), rentableRest);
        }

        return rentableUIMap;
    }


    public Map<Integer,RoomRest> convertRoomMapToRest(Map<Integer,RoomRA> map){
        Map<Integer,RoomRest> roomRestMap = new HashMap<>();
        for (Map.Entry<Integer,RoomRA> entry : map.entrySet()
        ) {
            RoomRest roomRest = (RoomRest) convertToRest(entry.getValue());
            roomRestMap.put(entry.getKey(), roomRest);
        }

        return roomRestMap;
    }

    public Map<Integer,SaunaRest> convertSaunaMapToRest(Map<Integer,SaunaRA> map){
        Map<Integer,SaunaRest> saunaRestMap = new HashMap<>();
        for (Map.Entry<Integer,SaunaRA> entry : map.entrySet()
        ) {
            SaunaRest saunaRest = (SaunaRest) convertToRest(entry.getValue());
            saunaRestMap.put(entry.getKey(),saunaRest);
        }

        return saunaRestMap;
    }
}

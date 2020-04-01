package pl.lodz.p.it.Converters.UI;

import Model.RentableUI;
import Model.RoomUI;
import Model.SaunaUI;
import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;

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
}

package pl.lodz.p.it.Converters.Repository;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import pl.lodz.p.it.RA.Model.RentableRA;
import pl.lodz.p.it.RA.Model.RoomRA;
import pl.lodz.p.it.RA.Model.SaunaRA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RentableConverterRepository {
    public Rentable convertToDomain(RentableRA rentableRA){
        if(rentableRA instanceof RoomRA){
            return new Room(
                    rentableRA.getNumber(),
                    ((RoomRA) rentableRA).getArea(),
                    ((RoomRA) rentableRA).getBeds()
            );
        }
        if(rentableRA instanceof SaunaRA){
            return new Sauna(
                    rentableRA.getNumber(),
                    ((SaunaRA) rentableRA).getPricePerHour()
            );
        }
        return null;
    }

    public RentableRA convertToRepository(Rentable rentable){
        if(rentable instanceof Room){
            return new RoomRA(
                    rentable.getNumber(),
                    ((Room) rentable).getArea(),
                    ((Room) rentable).getBeds()
            );
        }
        if(rentable instanceof Sauna){
            return new SaunaRA(
                    rentable.getNumber(),
                    ((Sauna) rentable).getPricePerHour()
            );
        }
        return null;
    }
}

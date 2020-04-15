package pl.lodz.p.it.applicationPorts.Converters.Repository;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Rent;
import com.mycompany.store.Model.Rentable;
import pl.lodz.p.it.RA.Model.ClientRA;
import pl.lodz.p.it.RA.Model.RentRA;
import pl.lodz.p.it.RA.Model.RentableRA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RentConverterRepository {

    @Inject
    RentableConverterRepository rentableConverter;

    @Inject
    UserConverterRepository userConverter;

    public RentRA convertToRepository(Rent rent){
        RentableRA rentableRA = rentableConverter.convertToRepository(rent.getRentable());
        ClientRA clientRA = (ClientRA) userConverter.convertToRepository(rent.getClient());
        return new RentRA(
                rent.getId(),
                rentableRA,
                clientRA,
                rent.getRentStart(),
                rent.getRentStop());
    }

    public Rent convertToDomain(RentRA rent){
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

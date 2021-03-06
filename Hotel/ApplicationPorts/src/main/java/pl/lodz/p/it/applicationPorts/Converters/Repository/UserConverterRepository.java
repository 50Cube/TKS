package pl.lodz.p.it.applicationPorts.Converters.Repository;


import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import lombok.extern.java.Log;
import pl.lodz.p.it.RA.Model.AdminRA;
import pl.lodz.p.it.RA.Model.ClientRA;
import pl.lodz.p.it.RA.Model.ManagerRA;
import pl.lodz.p.it.RA.Model.UserRA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Log
@Named
@ApplicationScoped
public class UserConverterRepository {
    public UserRA convertToRepository(User user){
        if (user instanceof Admin) {
            return new AdminRA(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof Manager) {
            return new ManagerRA(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof Client) {
            return new ClientRA(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }

    public User convertToDomain(UserRA user){
        if (user instanceof AdminRA) {
            return new Admin(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ManagerRA) {
            return new Manager(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ClientRA) {
            return new Client(
                    user.getLogin(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }
}


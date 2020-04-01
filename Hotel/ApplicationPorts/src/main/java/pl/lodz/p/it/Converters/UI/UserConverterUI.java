package pl.lodz.p.it.Converters.UI;

import Model.AdminUI;
import Model.ClientUI;
import Model.ManagerUI;
import Model.UserUI;
import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;

public class UserConverterUI {
    public UserUI convertToUI(User user){
        if (user instanceof Admin) {
            return new AdminUI(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof Manager) {
            return new ManagerUI(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof Client) {
            return new ClientUI(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }

    public User convertToDomain(UserUI user){
        if (user instanceof AdminUI) {
            return new Admin(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ManagerUI) {
            return new Manager(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ClientUI) {
            return new Client(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }
}

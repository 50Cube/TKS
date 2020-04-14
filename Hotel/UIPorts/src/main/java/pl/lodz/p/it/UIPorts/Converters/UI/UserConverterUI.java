package pl.lodz.p.it.UIPorts.Converters.UI;



import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import pl.lodz.p.it.UIModel.AdminUI;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.ManagerUI;
import pl.lodz.p.it.UIModel.UserUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
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

    public Client convertClientToDomain(ClientUI user){
        return new Client(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getIsActive()
        );
    }

    public Map<String,ClientUI> convertClientMapToUI(Map<String,Client> userMap){
        Map<String,ClientUI> userUIMap = new HashMap<>();
        for (Map.Entry<String,Client> entry : userMap.entrySet()
             ) {
            ClientUI userUI = (ClientUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }

    public Map<String,UserUI> convertUserMapToUI(Map<String,User> userMap){
        Map<String,UserUI> userUIMap = new HashMap<>();
        for (Map.Entry<String,User> entry : userMap.entrySet()
        ) {
            UserUI userUI = (UserUI) convertToUI(entry.getValue());
            userUIMap.put(entry.getKey(),userUI);
        }

        return userUIMap;
    }
}

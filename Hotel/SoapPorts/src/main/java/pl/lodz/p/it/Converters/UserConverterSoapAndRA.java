package pl.lodz.p.it.Converters;

import pl.lodz.p.it.RA.Model.*;
import pl.lodz.p.it.SoapModel.AdminSoap;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.ManagerSoap;
import pl.lodz.p.it.SoapModel.UserSoap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Named
public class UserConverterSoapAndRA {
    public UserSoap convertToSoap(UserRA user){
        if (user instanceof AdminRA) {
            return new AdminSoap(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ManagerRA) {
            return new ManagerSoap(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ClientRA) {
            return new ClientSoap(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }

    public UserRA convertToRA(UserSoap user){
        if (user instanceof AdminSoap) {
            return new AdminRA(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ManagerSoap) {
            return new ManagerRA(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }

        if (user instanceof ClientSoap) {
            return new ClientRA(
                    user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    user.getSurname(),
                    user.getIsActive()
            );
        }
        return null;
    }

    public Map<String, UserSoap> convertUserMapToSoap(Map<String, UserRA> map) {
        Map<String, UserSoap> userRAMap = new HashMap<>();
        for (Map.Entry<String, UserRA> entry : map.entrySet()
        ) {
            UserSoap userSoap = (UserSoap) convertToSoap(entry.getValue());
            userRAMap.put(entry.getKey(), userSoap);
        }

        return userRAMap;
    }

    public Map<String, ClientSoap> convertClientMapToSoap(Map<String, ClientRA> map) {
        Map<String, ClientSoap> userRAMap = new HashMap<>();
        for (Map.Entry<String, ClientRA> entry : map.entrySet()
        ) {
            ClientSoap clientSoap = (ClientSoap) convertToSoap(entry.getValue());
            userRAMap.put(entry.getKey(), clientSoap);
        }

        return userRAMap;
    }
}

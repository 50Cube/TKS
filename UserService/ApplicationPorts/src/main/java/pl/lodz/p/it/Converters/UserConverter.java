package pl.lodz.p.it.Converters;

import pl.lodz.p.it.Model.UserRA;
import pl.lodz.p.it.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserConverter {

    public UserRA convertToRepository(User user) {
        return new UserRA(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getIsActive(),
                user.getGroup().toString()
        );
    }

    public User convertToDomain(UserRA user) {
        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getIsActive(),
                user.getGroup().toString()
        );
    }

    public Map<String, User> convertUserMapToDomain(Map<String, UserRA> userRAMap) {
        Map<String, User> userMap = new HashMap<>();
        for(Map.Entry<String, UserRA> entry : userRAMap.entrySet()) {
            User user = convertToDomain(entry.getValue());
            userMap.put(entry.getKey(), user);
        }
        return userMap;
    }
}

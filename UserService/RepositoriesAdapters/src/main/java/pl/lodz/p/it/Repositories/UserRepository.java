package pl.lodz.p.it.Repositories;

import pl.lodz.p.it.Model.UserRA;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserRepository {

    private Map<String, UserRA> users;

    public Map<String, UserRA> getUsers() {
        return new HashMap<>();
    }

    public UserRA getUser(String login) {
        return this.users.get(login);
    }

    public synchronized void addUser(UserRA user)
    {
        users.put(user.getLogin(), user);
    }

    public synchronized void updateUser(UserRA user, String newPassword, String newName, String newSurname)
    {
        UserRA localUser = getUser(user.getLogin());
        localUser.setPassword(newPassword);
        localUser.setName(newName);
        localUser.setSurname(newSurname);
    }

    public void activateUser(UserRA user)
    {
        UserRA localUser = getUser(user.getLogin());
        localUser.setIsActive(true);
    }

    public void deactivateUser(UserRA user)
    {
        UserRA localUser = getUser(user.getLogin());
        localUser.setIsActive(false);
    }

    public Map<String, UserRA> getFilteredUsers(String input) {
        Map<String, UserRA> tmp = new HashMap<>();

        users.values().stream().filter((user) -> ((user.toFilterString().toLowerCase()).contains(input.trim()))).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), user);
        });

        return tmp;
    }
}

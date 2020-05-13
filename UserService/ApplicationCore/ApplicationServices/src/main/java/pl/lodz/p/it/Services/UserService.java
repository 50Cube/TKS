package pl.lodz.p.it.Services;

import pl.lodz.p.it.Aggregates.UserAdapter;
import pl.lodz.p.it.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ApplicationScoped
public class UserService implements Serializable {

    @Inject
    private UserAdapter userAdapter;

    public UserService() {}

    public Map<String, User> getUsers() {
        return userAdapter.getUsers();
    }

    public User getUser(String login)
    {
        return userAdapter.getUser(login);
    }

    public void updateUser(String login, String newPassword, String newName, String newSurname)
    {
        userAdapter.updateUser(userAdapter.getUser(login), newPassword, newName, newSurname);
    }

    public void activateUser(String login)
    {
        if(!userAdapter.getUser(login).getIsActive())
            userAdapter.activateUser(userAdapter.getUser(login));
    }

    public void deactivateUser(String login)
    {
        if(userAdapter.getUser(login).getIsActive())
            userAdapter.deactivateUser(userAdapter.getUser(login));
    }

    public Map<String, User> getFilterUsers(String input) {
        return userAdapter.getFilteredUsers(input);
    }
}

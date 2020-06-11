package pl.lodz.p.it.Repositories;

import pl.lodz.p.it.Model.UserRA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserRepository {

    private Map<String, UserRA> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public Map<String, UserRA> getUsers() {
        return new HashMap<>(users);
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

    @PostConstruct
    private void initDataUser()
    {
        UserRA admin = new UserRA("admin", "1", "Norbercki", "Gierczak", true, "ADMIN");
        UserRA admin2 = new UserRA("4", "4", "Norbi", "Gierczak", true, "ADMIN");

        UserRA manager = new UserRA("manager", "2", "Marcin", "Krasucki", true, "MANAGER");
        UserRA client1 = new UserRA("client1", "3", "Gabriel", "Nowak", true, "CLIENT");
        UserRA client2 = new UserRA("client2", "4", "Jakub", "Bogdan", true, "CLIENT");
        UserRA client3 = new UserRA("client3", "5", "Szymon", "Rutkowski", false, "CLIENT");

        users.put(admin.getLogin(), admin);
        users.put(admin2.getLogin(), admin2);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }
}

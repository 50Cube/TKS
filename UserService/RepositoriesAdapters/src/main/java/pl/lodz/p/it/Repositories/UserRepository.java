package pl.lodz.p.it.Repositories;

import lombok.extern.java.Log;
import pl.lodz.p.it.Model.UserRA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Log
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
        UserRA admin = new UserRA("admin", "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b", "Norbercki", "Gierczak", true, "ADMIN");
        UserRA admin2 = new UserRA("4", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "Norbi", "Gierczak", true, "ADMIN");

        UserRA manager = new UserRA("manager", "d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35", "Marcin", "Krasucki", true, "MANAGER");
        UserRA client1 = new UserRA("client1", "4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce", "Gabriel", "Nowak", true, "CLIENT");
        UserRA client2 = new UserRA("client2", "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a", "Jakub", "Bogdan", true, "CLIENT");
        UserRA client3 = new UserRA("client3", "ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d", "Szymon", "Rutkowski", false, "CLIENT");

        users.put(admin.getLogin(), admin);
        users.put(admin2.getLogin(), admin2);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }

    public void removeUser(String login) {
        log.info("UserService:Attempting to remove user" + login);
        users.remove(login);
    }
}

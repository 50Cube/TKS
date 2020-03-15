package pl.lodz.p.it.RA.Repositories;

import pl.lodz.p.it.RA.Model.AdminRA;
import pl.lodz.p.it.RA.Model.ClientRA;
import pl.lodz.p.it.RA.Model.ManagerRA;
import pl.lodz.p.it.RA.Model.UserRA;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named(value = "userRepositoryRA")
@ApplicationScoped
public class UserRepositoryRA {

    private Map<String, UserRA> users;
    
    public UserRepositoryRA() {
        users = new HashMap<>();
    }
    
    public Map<String, UserRA> getUsers()
    {
        return new HashMap<>(users);
    }
    
    public UserRA getUser(String login)
    {
        return this.users.get(login);
    }
    
    public int getUsersAmount()
    {
        return users.size();
    }
    
    public synchronized void addUser(UserRA user)
    {
        users.put(user.getLogin(), user);
    }
    
    public synchronized void updateUser(UserRA user, String newPassword, String newName, String newSurname)
    {
        user.setPassword(newPassword);
        user.setName(newName);
        user.setSurname(newSurname);
    }
    
    public void activateUser(UserRA user)
    {
        user.setIsActive(true);
    }
    
    public void deactivateUser(UserRA user)
    {
        user.setIsActive(false);
    }
    
    public Map<String, UserRA> getFilteredUsers(String input) {
        Map<String, UserRA> tmp = new HashMap<>();
        
        users.values().stream().filter((user) -> ((user.toFilterString().toLowerCase()).contains(input.trim()))).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), user);
        });
        
        return tmp;
    }
    
    public Map<String, ClientRA> getClients() {
        Map<String, ClientRA> tmp = new HashMap<>();
        
        users.values().stream().filter((user) -> (user instanceof ClientRA)).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), (ClientRA) user);
        });
        
        return tmp;
    }
    
    
    @PostConstruct
    private void initDataUser()
    {
        AdminRA admin = new AdminRA("admin", "1", "Norbercki", "Gierczak", true);
        AdminRA admin2 = new AdminRA("4", "4", "Norbi", "Gierczak", true);

        ManagerRA manager = new ManagerRA("manager", "2", "Marcin", "Krasucki", true);
        ClientRA client1 = new ClientRA("client1", "3", "Gabriel", "Nowak", true);
        ClientRA client2 = new ClientRA("client2", "4", "Jakub", "Bogdan", true);
        ClientRA client3 = new ClientRA("client3", "5", "Szymon", "Rutkowski", false);
        
        users.put(admin.getLogin(), admin);
        users.put(admin2.getLogin(), admin2);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }
}
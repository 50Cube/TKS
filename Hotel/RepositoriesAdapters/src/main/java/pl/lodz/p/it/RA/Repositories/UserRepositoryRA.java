package pl.lodz.p.it.RA.Repositories;

import lombok.extern.java.Log;
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
@Log
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
        log.info("Hotel: user added " +user.getLogin());
    }

    public synchronized void addClient(ClientRA client) {
        users.put(client.getLogin(), client);
    }

    public synchronized void addAdmin(AdminRA admin) {
        users.put(admin.getLogin(), admin);
    }

    public synchronized void addManager(ManagerRA manager) {
        users.put(manager.getLogin(), manager);
    }
    
    public synchronized void updateUser(UserRA user, String newName, String newSurname)
    {
        UserRA localUser = getUser(user.getLogin());
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
        AdminRA admin = new AdminRA("admin", "Norbercki", "Gierczak", true);
        AdminRA admin2 = new AdminRA("4", "Norbi", "Gierczak", true);

        ManagerRA manager = new ManagerRA("manager", "Marcin", "Krasucki", true);
        ClientRA client1 = new ClientRA("client1", "Gabriel", "Nowak", true);
        ClientRA client2 = new ClientRA("client2","Jakub", "Bogdan", true);
        ClientRA client3 = new ClientRA("client3", "Szymon", "Rutkowski", false);
        
        users.put(admin.getLogin(), admin);
        users.put(admin2.getLogin(), admin2);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }

    public void removeUser(String login) {
        users.remove(login);
        log.info("Hotel: user removed" + login);
    }
}
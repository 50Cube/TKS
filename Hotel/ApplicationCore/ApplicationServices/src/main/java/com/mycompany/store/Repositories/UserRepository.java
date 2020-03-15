package com.mycompany.store.Repositories;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "userRepository")
@ApplicationScoped
public class UserRepository {

    private Map<String, User> users;
    
    public UserRepository() {
        users = new HashMap<>();
    }
    
    public Map<String, User> getUsers()
    {
        return new HashMap<>(users);
    }
    
    public User getUser(String login)
    {
        return this.users.get(login);
    }
    
    public int getUsersAmount()
    {
        return users.size();
    }
    
    public synchronized void addUser(User user)
    {
        users.put(user.getLogin(), user);
    }
    
    public synchronized void updateUser(User user, String newPassword, String newName, String newSurname)
    {
        user.setPassword(newPassword);
        user.setName(newName);
        user.setSurname(newSurname);
    }
    
    public void activateUser(User user)
    {
        user.setIsActive(true);
    }
    
    public void deactivateUser(User user)
    {
        user.setIsActive(false);
    }
    
    public Map<String, User> getFilteredUsers(String input) {
        Map<String, User> tmp = new HashMap<>();
        
        users.values().stream().filter((user) -> ((user.toFilterString().toLowerCase()).contains(input.trim()))).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), user);
        });
        
        return tmp;
    }
    
    public Map<String, Client> getClients() {
        Map<String, Client> tmp = new HashMap<>();
        
        users.values().stream().filter((user) -> (user instanceof Client)).forEachOrdered((user) -> {
            tmp.put(user.getLogin(), (Client) user);
        });
        
        return tmp;
    }
    
    
    @PostConstruct
    private void initDataUser()
    {
        Admin admin = new Admin("admin", "1", "Norbercki", "Gierczak", true);
        Admin admin2 = new Admin("4", "4", "Norbi", "Gierczak", true);

        Manager manager = new Manager("manager", "2", "Marcin", "Krasucki", true);
        Client client1 = new Client("client1", "3", "Gabriel", "Nowak", true);
        Client client2 = new Client("client2", "4", "Jakub", "Bogdan", true);
        Client client3 = new Client("client3", "5", "Szymon", "Rutkowski", false);
        
        users.put(admin.getLogin(), admin);
        users.put(admin2.getLogin(), admin2);
        users.put(manager.getLogin(), manager);
        users.put(client1.getLogin(), client1);
        users.put(client2.getLogin(), client2);
        users.put(client3.getLogin(), client3);
    }
}
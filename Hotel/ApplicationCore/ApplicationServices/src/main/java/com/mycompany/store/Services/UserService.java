package com.mycompany.store.Services;

import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import pl.lodz.p.it.applicationPorts.Aggregates.UserAdapter;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "userService")
@ApplicationScoped
public class UserService implements Serializable{

    @Inject
    private UserAdapter userAdapter;
    
    public UserService() {
    }
    
    
    public Map<String, User> getUsers()
    {
        return userAdapter.getUsers();
    }
    
    public User getUser(String login)
    {
        return userAdapter.getUser(login);
    }

    public void addClient(String login, String name, String surname, boolean active) throws IllegalArgumentException {
        if(!userAdapter.getUsers().containsKey(login))
            userAdapter.addUser(new Client(login,name,surname,active));
        else throw new IllegalArgumentException("User already exists");
    }
    
    public void addManager(String login, String name, String surname, boolean active) throws IllegalArgumentException {
        if(!userAdapter.getUsers().containsKey(login))
            userAdapter.addUser(new Manager(login,name,surname,active));
         else throw new IllegalArgumentException("User already exists");
    }
    
    public void addAdmin(String login, String name, String surname, boolean active) throws IllegalArgumentException {
        if(!userAdapter.getUsers().containsKey(login))
            userAdapter.addUser(new Admin(login,name,surname,active));
        else throw new IllegalArgumentException("User already exists");
    }
    
    public void updateUser(String login, String newName, String newSurname)
    {
        userAdapter.updateUser(userAdapter.getUser(login), newName, newSurname);
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

    public void removeUser(String login){
        userAdapter.removeUser(login);
    }
    
    public Map<String, User> getFilterUsers(String input) {
        return userAdapter.getFilteredUsers(input);
    }
    
    public Map<String, Client> getClients() {
        return userAdapter.getClients();
    }
}
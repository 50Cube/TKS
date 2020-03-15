package com.mycompany.store.Services;

import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Repositories.UserRepository;
import com.mycompany.store.Model.User;
import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;


@Named(value = "userService")
@Dependent
public class UserService implements Serializable{

    @Inject
    private UserRepository userRepository;
    
    public UserService() {
    }
    
    
    public Map<String, User> getUsers()
    {
        return userRepository.getUsers();
    }
    
    public User getUser(String login)
    {
        return userRepository.getUser(login);
    }
    
    public int getUsersAmount()
    {
        return userRepository.getUsersAmount();
    }
    
    public void addClient(String login, String password, String name, String surname, boolean active) throws Exception {
        if(!userRepository.getUsers().containsKey(login))
            userRepository.addUser(new Client(login,password,name,surname,active));
        else throw new Exception("User already exists");
    }
    
    public void addManager(String login, String password, String name, String surname, boolean active) throws Exception {
        if(!userRepository.getUsers().containsKey(login))
            userRepository.addUser(new Manager(login,password,name,surname,active));
         else throw new Exception("User already exists");
    }
    
    public void addAdmin(String login, String password, String name, String surname, boolean active) throws Exception {
        if(!userRepository.getUsers().containsKey(login))
            userRepository.addUser(new Admin(login,password,name,surname,active));
        else throw new Exception("User already exists");
    }
    
    public void updateUser(String login, String newPassword, String newName, String newSurname)
    {
        userRepository.updateUser(userRepository.getUser(login), newPassword, newName, newSurname);
    }
    
    public void activateUser(String login)
    {
        if(!userRepository.getUser(login).getIsActive())
            userRepository.activateUser(userRepository.getUser(login));
    }
    
    public void deactivateUser(String login)
    {
        if(userRepository.getUser(login).getIsActive())
            userRepository.deactivateUser(userRepository.getUser(login));
    }
    
    public Map<String, User> getFilterUsers(String input) {
        return userRepository.getFilteredUsers(input);
    }
    
    public Map<String, Client> getClients() {
        return userRepository.getClients();
    }
}
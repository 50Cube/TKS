package com.mycompany.store.Controllers;

import com.mycompany.store.Services.UserService;
import com.mycompany.store.Model.User;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;


@Named(value = "listUsersController")
@ViewScoped
public class listUsersController implements Serializable{
    
        
    @Inject
    private UserService userService;
    
    @Inject
    private DataHolder dh;
    
    private Map<String,User> users;
    private String filter;
    
    public listUsersController() {
    }
    
    @PostConstruct
    public void loadUsers()
    {
        users = userService.getUsers();
    }
    
    public Map<String, User> getUsers()
    {
        return users;
    }
    
    public User getUser(String name)
    {
        return this.userService.getUser(name);
    }
    
    public String saveData(User user) {
        dh.setUser(user);
        return "updateUser.xhtml";
    }
    
    public void getFilteredUsers() {
        users = userService.getFilterUsers(this.filter);
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    public void activate(User user) {
        if(user.getIsActive())
            userService.deactivateUser(user.getLogin());
        else userService.activateUser(user.getLogin());
        loadUsers();
    }
}

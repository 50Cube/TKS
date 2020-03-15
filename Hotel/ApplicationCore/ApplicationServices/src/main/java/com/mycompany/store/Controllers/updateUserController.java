package com.mycompany.store.Controllers;

import com.mycompany.store.Model.Admin;
import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.Manager;
import com.mycompany.store.Model.User;
import com.mycompany.store.Services.UserService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "updateUserController")
@RequestScoped
public class updateUserController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private UserService userService;
   
    private User user;
    
    public updateUserController() {
    }
    
    @PostConstruct
    private void init() {
        if(dh.getUser().getType().equals("Admin"))
            user = new Admin(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
        else if(dh.getUser().getType().equals("Manager"))
            user = new Manager(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
        else if(dh.getUser().getType().equals("Client"))
            user = new Client(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
    }
    
    public User getUser() {
        return this.user;
    }
    
    public String updateUser() {
        userService.updateUser(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        return "listUsers";
    }
}

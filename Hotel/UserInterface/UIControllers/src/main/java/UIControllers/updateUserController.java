package UIControllers;

import pl.lodz.p.it.Publisher;
import pl.lodz.p.it.UIModel.AdminUI;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.ManagerUI;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;

@Named(value = "updateUserController")
@RequestScoped
public class updateUserController implements Serializable {

    @Inject
    DataHolder dh;
    
    @Inject
    private UserAdapterUI userService;

    @Inject
    private Publisher publisher;
   
    private UserUI user;
    
    public updateUserController() {
    }
    
    @PostConstruct
    private void init() {
        if(dh.getUser().getType().equals("Admin"))
            user = new AdminUI(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
        else if(dh.getUser().getType().equals("Manager"))
            user = new ManagerUI(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
        else if(dh.getUser().getType().equals("Client"))
            user = new ClientUI(dh.getUser().getLogin(), dh.getUser().getPassword(), dh.getUser().getName(), dh.getUser().getSurname(), dh.getUser().getIsActive());
    }
    
    public UserUI getUser() {
        return this.user;
    }
    
    public String updateUser() {
        userService.updateUser(user.getLogin(), user.getName(), user.getSurname());

        String json = Json.createObjectBuilder()
                .add("login", user.getLogin())
                .add("password", user.getPassword())
                .add("name", user.getName())
                .add("surname", user.getSurname()).build().toString();

        try {
            publisher.updateUser(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "listUsers";
    }
}

package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import com.mycompany.store.Services.UserService;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named(value = "userAdapterUI")
@ApplicationScoped
public class UserAdapterUI {
    @Inject
    UserService userService;

    @Inject
    UserConverterUI converter;

    public Map<String, UserUI> getUsers() {
        Map<String, User> users = userService.getUsers();
        HashMap<String,UserUI> result = new HashMap<>();
        for(Map.Entry<String,User> entry : users.entrySet()){
            UserUI user = converter.convertToUI(entry.getValue());
            result.put(entry.getKey(),user);
        }
        return result;
    }

    public UserUI getUser(String login) {
        return converter.convertToUI(userService.getUser(login));
    }

    public void addClient(String login, String password, String name, String surname, boolean active) throws  Exception{
        userService.addClient(login,password,name,surname,active);
    }

    public void addManager(String login, String password, String name, String surname, boolean active) throws  Exception{
        userService.addManager(login,password,name,surname,active);
    }

    public void addAdmin(String login, String password, String name, String surname, boolean active) throws  Exception{
        userService.addAdmin(login,password,name,surname,active);
    }

    public void updateUser(String login, String newPassword, String newName, String newSurname)
    {
        userService.updateUser(login,newPassword,newName,newSurname);
    }

    public void activateUser(String login)
    {
       userService.activateUser(login);
    }

    public void deactivateUser(String login)
    {
        userService.deactivateUser(login);
    }

    public Map<String, UserUI> getFilterUsers(String input) {
        Map<String, UserUI> result = new HashMap<>();
        Map<String, User> filteredUsers = userService.getFilterUsers(input);

        for(Map.Entry<String,User> entry : filteredUsers.entrySet()){
            UserUI user = converter.convertToUI(entry.getValue());
            result.put(entry.getKey(),user);
        }
        return result;
    }

    public Map<String, ClientUI> getClients() {
        Map<String, User> users = userService.getUsers();
        HashMap<String,ClientUI> result = new HashMap<>();
        for(Map.Entry<String,User> entry : users.entrySet()){
            if(entry.getValue() instanceof Client){
                ClientUI user = (ClientUI) converter.convertToUI(entry.getValue());
                result.put(entry.getKey(),user);
            }
        }
        return result;
    }

}

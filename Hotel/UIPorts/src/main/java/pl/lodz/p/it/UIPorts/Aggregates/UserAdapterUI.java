package pl.lodz.p.it.UIPorts.Aggregates;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import com.mycompany.store.Services.UserService;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;
import pl.lodz.p.it.UIPorts.Ports.UserPorts.AddUserPort;
import pl.lodz.p.it.UIPorts.Ports.UserPorts.ChangeUserActivenessPort;
import pl.lodz.p.it.UIPorts.Ports.UserPorts.GetUserPort;
import pl.lodz.p.it.UIPorts.Ports.UserPorts.UpdateUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named(value = "userAdapterUI")
@ApplicationScoped
public class UserAdapterUI implements AddUserPort, ChangeUserActivenessPort, GetUserPort, UpdateUserPort {
    @Inject
    UserService userService;

    @Inject
    UserConverterUI converter;
    @Override
    public Map<String, UserUI> getUsers() {
        Map<String, User> users = userService.getUsers();
        HashMap<String,UserUI> result = new HashMap<>();
        for(Map.Entry<String,User> entry : users.entrySet()){
            UserUI user = converter.convertToUI(entry.getValue());
            result.put(entry.getKey(),user);
        }
        return result;
    }
    @Override
    public UserUI getUser(String login) {
        return converter.convertToUI(userService.getUser(login));
    }
    @Override
    public void addClient(String login, String name, String surname, boolean active) throws  Exception{
        userService.addClient(login,name,surname,active);
    }
    @Override
    public void addManager(String login, String name, String surname, boolean active) throws  Exception{
        userService.addManager(login,name,surname,active);
    }
    @Override
    public void addAdmin(String login, String name, String surname, boolean active) throws  Exception{
        userService.addAdmin(login,name,surname,active);
    }
    @Override
    public void updateUser(String login, String newName, String newSurname)
    {
        userService.updateUser(login,newName,newSurname);
    }
    @Override
    public void activateUser(String login)
    {
       userService.activateUser(login);
    }
    @Override
    public void deactivateUser(String login)
    {
        userService.deactivateUser(login);
    }
    @Override
    public Map<String, UserUI> getFilterUsers(String input) {
        Map<String, UserUI> result = new HashMap<>();
        Map<String, User> filteredUsers = userService.getFilterUsers(input);

        for(Map.Entry<String,User> entry : filteredUsers.entrySet()){
            UserUI user = converter.convertToUI(entry.getValue());
            result.put(entry.getKey(),user);
        }
        return result;
    }
    @Override
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

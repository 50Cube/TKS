package pl.lodz.p.it.Aggregates;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import pl.lodz.p.it.Converters.Repository.UserConverterRepository;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.GetFilteredUsersPort;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.GetUserPort;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.GetUsersPort;
import pl.lodz.p.it.RA.Model.ClientRA;
import pl.lodz.p.it.RA.Model.UserRA;
import pl.lodz.p.it.RA.Repositories.UserRepositoryRA;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.AddUserPort;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.ChangeUserActivenessPort;
import pl.lodz.p.it.InfrastructurePorts.UserPorts.UpdateUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named(value = "userAdapter")
@ApplicationScoped
public class UserAdapter implements AddUserPort, ChangeUserActivenessPort, UpdateUserPort, GetUserPort, GetUsersPort, GetFilteredUsersPort {
    @Inject
    private UserRepositoryRA userRepository;
    @Inject
    private UserConverterRepository converter;

    @Override
    public Map<String, User> getFilteredUsers(String input) {
        Map<String, User> result = new HashMap<>();
        Map<String, UserRA> filteredUsers = userRepository.getFilteredUsers(input);

        for(Map.Entry<String,UserRA> entry : filteredUsers.entrySet()){
            User user = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),user);
        }

        return result;
    }

    @Override
    public User getUser(String login) {
        return converter.convertToDomain(userRepository.getUser(login));
    }

    @Override
    public Map<String, User> getUsers() {
        Map<String, UserRA> users = userRepository.getUsers();
        HashMap<String,User> result = new HashMap<>();
        for(Map.Entry<String,UserRA> entry : users.entrySet()){
            User user = converter.convertToDomain(entry.getValue());
            result.put(entry.getKey(),user);
        }
        return result;
    }

    @Override
    public Map<String, Client> getClients() {
        Map<String, UserRA> users = userRepository.getUsers();
        HashMap<String,Client> result = new HashMap<>();
        for(Map.Entry<String,UserRA> entry : users.entrySet()){
            if(entry.getValue() instanceof ClientRA){
                Client user = (Client) converter.convertToDomain(entry.getValue());
                result.put(entry.getKey(),user);
            }
        }
        return result;
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(converter.convertToRepository(user));
    }

    @Override
    public void activateUser(User user) {
        userRepository.activateUser(converter.convertToRepository(user));
    }

    @Override
    public void deactivateUser(User user) {
        userRepository.deactivateUser(converter.convertToRepository(user));
    }

    @Override
    public void updateUser(User user, String newPassword, String newName, String newSurname) {
        UserRA userRA = converter.convertToRepository(user);
        userRepository.updateUser(userRA,newPassword,newName,newSurname);
    }
}

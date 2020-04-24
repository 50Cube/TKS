package pl.lodz.p.it.Aggregates;

import pl.lodz.p.it.Converters.UserConverterSoapAndRA;
import pl.lodz.p.it.Ports.Soap.*;
import pl.lodz.p.it.RA.Repositories.UserRepositoryRA;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.UserSoap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@ApplicationScoped
@Named
public class UserAdapterSoapAndRA implements AddUserPort, ChangeUserActivenessPort, GetFilteredUsersPort, GetUserPort, GetUsersPort, UpdateUserPort {

    @Inject
    UserRepositoryRA userRepository;

    @Inject
    UserConverterSoapAndRA converter;

    @Override
    public void addUser(UserSoap user) {
        userRepository.addUser(converter.convertToRA(user));
    }

    @Override
    public void activateUser(UserSoap user) {
        userRepository.activateUser(converter.convertToRA(user));
    }

    @Override
    public void deactivateUser(UserSoap user) {
        userRepository.deactivateUser(converter.convertToRA(user));
    }

    @Override
    public Map<String, UserSoap> getFilteredUsers(String input) {
        return converter.convertUserMapToSoap(userRepository.getFilteredUsers(input));
    }

    @Override
    public UserSoap getUser(String login) {
        return converter.convertToSoap(userRepository.getUser(login));
    }

    @Override
    public Map<String, UserSoap> getUsers() {
        return converter.convertUserMapToSoap(userRepository.getUsers());
    }

    @Override
    public Map<String, ClientSoap> getClients() {
        return converter.convertClientMapToSoap(userRepository.getClients());
    }

    @Override
    public void updateUser(UserSoap user, String newPassword, String newName, String newSurname) {
        userRepository.updateUser(converter.convertToRA(user), newPassword, newName, newSurname);
    }
}

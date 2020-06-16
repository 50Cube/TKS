package pl.lodz.p.it.Aggregates;

import pl.lodz.p.it.Converters.UserConverter;
import pl.lodz.p.it.Ports.*;
import pl.lodz.p.it.Repositories.UserRepository;
import pl.lodz.p.it.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class UserAdapter implements AddUserPort, ChangeUserActivenessPort, UpdateUserPort, GetUserPort, GetUsersPort, GetFilteredUsersPort {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserConverter converter;

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
    public Map<String, User> getFilteredUsers(String input) {
        return converter.convertUserMapToDomain(userRepository.getFilteredUsers(input));
    }

    @Override
    public User getUser(String login) {
        return converter.convertToDomain(userRepository.getUser(login));
    }

    @Override
    public Map<String, User> getUsers() {
        return converter.convertUserMapToDomain(userRepository.getUsers());
    }

    @Override
    public void updateUser(User user, String newPassword, String newName, String newSurname) {
        userRepository.updateUser(converter.convertToRepository(user), newPassword, newName, newSurname);
    }

    public void removeUser(String login) {
        userRepository.removeUser(login);
    }
}

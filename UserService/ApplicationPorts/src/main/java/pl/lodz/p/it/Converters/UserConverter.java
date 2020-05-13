package pl.lodz.p.it.Converters;

import pl.lodz.p.it.Model.UserRA;
import pl.lodz.p.it.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserConverter {

    public UserRA convertToRepository(User user) {
        return new UserRA(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getIsActive(),
                user.getGroup()
        );
    }

    public User convertToDomain(UserRA user) {
        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getIsActive(),
                user.getGroup()
        );
    }
}

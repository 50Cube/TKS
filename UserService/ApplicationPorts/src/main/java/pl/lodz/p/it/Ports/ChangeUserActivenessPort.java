package pl.lodz.p.it.Ports;

import pl.lodz.p.it.User;

public interface ChangeUserActivenessPort {
    void activateUser(User user);
    void deactivateUser(User user);
}

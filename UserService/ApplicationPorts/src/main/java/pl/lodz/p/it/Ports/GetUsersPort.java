package pl.lodz.p.it.Ports;

import pl.lodz.p.it.User;

import java.util.Map;

public interface GetUsersPort {
    Map<String, User> getUsers();
}

package pl.lodz.p.it.Ports;

import pl.lodz.p.it.User;

import java.util.Map;

public interface GetFilteredUsersPort {
    Map<String, User> getFilteredUsers(String input);
}

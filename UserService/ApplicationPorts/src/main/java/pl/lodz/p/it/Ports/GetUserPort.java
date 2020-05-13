package pl.lodz.p.it.Ports;

import pl.lodz.p.it.User;

public interface GetUserPort {
    User getUser(String login);
}

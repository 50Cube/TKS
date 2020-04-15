package pl.lodz.p.it.UIPorts.Ports.UserPorts;

import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.UserUI;

import java.util.Map;

public interface GetUserPort {
    UserUI getUser(String login);
    Map<String, UserUI> getUsers();
    Map<String, ClientUI> getClients();
    Map<String, UserUI> getFilterUsers(String input);
}

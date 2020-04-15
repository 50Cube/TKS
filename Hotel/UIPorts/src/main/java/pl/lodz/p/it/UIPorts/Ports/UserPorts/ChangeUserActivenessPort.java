package pl.lodz.p.it.UIPorts.Ports.UserPorts;

public interface ChangeUserActivenessPort {
    void activateUser(String login);
    void deactivateUser(String login);
}

package pl.lodz.p.it.Ports.UI;

public interface ChangeUserActivenessPort {
    void activateUser(String login);
    void deactivateUser(String login);
}

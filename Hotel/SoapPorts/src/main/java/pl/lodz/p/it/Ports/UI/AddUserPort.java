package pl.lodz.p.it.Ports.UI;

public interface AddUserPort {
    void addClient(String login, String password, String name, String surname, boolean active);
    void addManager(String login, String password, String name, String surname, boolean active);
    void addAdmin(String login, String password, String name, String surname, boolean active);
}

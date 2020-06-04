package pl.lodz.p.it.UIPorts.Ports.UserPorts;

public interface AddUserPort {
    void addClient(String login, String name, String surname, boolean active)  throws  Exception;
    void addManager(String login, String name, String surname, boolean active)  throws  Exception;
    void addAdmin(String login, String name, String surname, boolean active)  throws  Exception;
}

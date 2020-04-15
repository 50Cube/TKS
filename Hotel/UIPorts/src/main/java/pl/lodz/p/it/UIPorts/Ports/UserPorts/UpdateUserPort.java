package pl.lodz.p.it.UIPorts.Ports.UserPorts;

public interface UpdateUserPort {
    void updateUser(String login, String newPassword, String newName, String newSurname);
}

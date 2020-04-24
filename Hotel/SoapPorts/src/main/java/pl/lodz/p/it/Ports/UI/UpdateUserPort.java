package pl.lodz.p.it.Ports.UI;

public interface UpdateUserPort {
    void updateUser(String login, String newPassword, String newName, String newSurname);
}

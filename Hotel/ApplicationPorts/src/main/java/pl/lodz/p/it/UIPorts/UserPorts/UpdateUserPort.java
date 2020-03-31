package pl.lodz.p.it.UIPorts.UserPorts;

import com.mycompany.store.Model.User;

public interface UpdateUserPort {
    void updateUser(User user, String newPassword, String newName, String newSurname);
}

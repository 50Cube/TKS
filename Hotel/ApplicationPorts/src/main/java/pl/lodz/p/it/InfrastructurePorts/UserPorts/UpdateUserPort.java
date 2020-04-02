package pl.lodz.p.it.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;

public interface UpdateUserPort {
    void updateUser(User user, String newPassword, String newName, String newSurname);
}

package pl.lodz.p.it.applicationPorts.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;

public interface UpdateUserPort {
    void updateUser(User user, String newName, String newSurname);
}

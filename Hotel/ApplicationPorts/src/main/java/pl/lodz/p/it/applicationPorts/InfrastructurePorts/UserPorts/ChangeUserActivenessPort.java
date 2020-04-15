package pl.lodz.p.it.applicationPorts.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;

public interface ChangeUserActivenessPort {
    void activateUser(User user);
    void deactivateUser(User user);
}

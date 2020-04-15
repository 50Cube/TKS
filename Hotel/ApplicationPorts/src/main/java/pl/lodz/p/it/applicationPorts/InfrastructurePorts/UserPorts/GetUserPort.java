package pl.lodz.p.it.applicationPorts.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;

public interface GetUserPort {
    User getUser(String login);
}

package pl.lodz.p.it.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;

public interface GetUserPort {
    User getUser(String login);
}

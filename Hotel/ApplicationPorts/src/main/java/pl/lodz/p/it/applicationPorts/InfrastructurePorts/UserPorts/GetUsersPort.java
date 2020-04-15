package pl.lodz.p.it.applicationPorts.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.Client;
import com.mycompany.store.Model.User;
import java.util.Map;

public interface GetUsersPort {
    Map<String, User> getUsers();
    Map<String, Client> getClients();
}

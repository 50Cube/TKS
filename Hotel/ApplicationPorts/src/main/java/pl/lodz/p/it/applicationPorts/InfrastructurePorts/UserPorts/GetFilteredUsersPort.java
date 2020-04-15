package pl.lodz.p.it.applicationPorts.InfrastructurePorts.UserPorts;

import com.mycompany.store.Model.User;
import java.util.Map;

public interface GetFilteredUsersPort {
    Map<String, User> getFilteredUsers(String input);
}

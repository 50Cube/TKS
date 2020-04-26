package pl.lodz.p.it;

import pl.lodz.p.it.Aggregates.UserAdapterSoapAndRA;
import pl.lodz.p.it.SoapModel.AdminSoap;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.ManagerSoap;
import pl.lodz.p.it.SoapModel.UserSoap;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Map;

@Named
@ApplicationScoped
@WebService
public class SoapUserService {

    @Inject
    UserAdapterSoapAndRA userAdapter;

    public SoapUserService() { }

    @WebMethod
    public Map<String, UserSoap> getUsers() {
        return userAdapter.getUsers();
    }

    @WebMethod
    public Map<String, UserSoap> getFilteredUsers(@WebParam String input) {
        return userAdapter.getFilteredUsers(input);
    }

    @WebMethod
    public Map<String, ClientSoap> getClients() {
        return userAdapter.getClients();
    }

    @WebMethod
    public UserSoap getUser(@WebParam String login) {
        return userAdapter.getUser(login);
    }

    @WebMethod
    public void addClient(@WebParam ClientSoap client) {
        userAdapter.addClient(client);
    }

    @WebMethod
    public void addAdmin(@WebParam AdminSoap admin) {
        userAdapter.addAdmin(admin);
    }

    @WebMethod
    public void addManager(@WebParam ManagerSoap manager) {
        userAdapter.addManager(manager);
    }

    @WebMethod
    public void updateUser(@WebParam UserSoap user) {
        userAdapter.updateUser(user, user.getPassword(), user.getName(), user.getSurname());
    }
}

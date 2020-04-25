package pl.lodz.p.it;

import pl.lodz.p.it.Aggregates.UserAdapterUIAndSoap;
import pl.lodz.p.it.UIModel.AdminUI;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.ManagerUI;
import pl.lodz.p.it.UIModel.UserUI;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Map;

@Named
@RequestScoped
@WebService
public class SoapUserService {

    @Inject
    UserAdapterUIAndSoap userAdapter;

    public SoapUserService() { }

    @WebMethod
    public Map<String, UserUI> getUsers() {
        return userAdapter.getUsers();
    }

    @WebMethod
    public Map<String, UserUI> getFilteredUsers(@WebParam String input) {
        return userAdapter.getFilterUsers(input);
    }

    @WebMethod
    public Map<String, ClientUI> getClients() {
        return userAdapter.getClients();
    }

    @WebMethod
    public UserUI getUser(@WebParam String login) {
        return userAdapter.getUser(login);
    }

    @WebMethod
    public void addClient(@WebParam ClientUI client) {
        userAdapter.addClient(client.getLogin(), client.getPassword(), client.getName(), client.getSurname(), client.getIsActive());
    }

    @WebMethod
    public void addAdmin(@WebParam AdminUI admin) {
        userAdapter.addAdmin(admin.getLogin(), admin.getPassword(), admin.getName(), admin.getSurname(), admin.getIsActive());
    }

    @WebMethod
    public void addManager(@WebParam ManagerUI manager) {
        userAdapter.addManager(manager.getLogin(), manager.getPassword(), manager.getName(), manager.getSurname(), manager.getIsActive());
    }

    @WebMethod
    public void updateUser(@WebParam UserUI user) {
        userAdapter.updateUser(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
    }
}

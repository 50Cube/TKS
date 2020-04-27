package pl.lodz.p.it.impl;

import pl.lodz.p.it.Aggregates.UserAdapterSoapAndRA;
import pl.lodz.p.it.SoapModel.AdminSoap;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.ManagerSoap;
import pl.lodz.p.it.SoapModel.UserSoap;
import pl.lodz.p.it.SoapUserServiceInterface;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@Named
@ApplicationScoped
@WebService(endpointInterface = "pl.lodz.p.it.SoapUserServiceInterface")
public class SoapUserService implements SoapUserServiceInterface {

    @Inject
    UserAdapterSoapAndRA userAdapter;

    public SoapUserService() { }

    @Override
    public Map<String, UserSoap> getUsers() {
        return userAdapter.getUsers();
    }

    @Override
    public HashMap<String, UserSoap> getFilteredUsers(String input) {
        return (HashMap<String, UserSoap>) userAdapter.getFilteredUsers(input);
    }

    @Override
    public Map<String, ClientSoap> getClients() {
        return userAdapter.getClients();
    }

    @Override
    public UserSoap getUser(String login) {
        return userAdapter.getUser(login);
    }

    @Override
    public void addClient(ClientSoap client) {
        userAdapter.addClient(client);
    }

    @Override
    public void addAdmin(AdminSoap admin) {
        userAdapter.addAdmin(admin);
    }

    @Override
    public void addManager(ManagerSoap manager) {
        userAdapter.addManager(manager);
    }

    @Override
    public void updateUser(UserSoap user) {
        userAdapter.updateUser(user, user.getPassword(), user.getName(), user.getSurname());
    }
}

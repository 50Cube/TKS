package pl.lodz.p.it.impl;

import pl.lodz.p.it.Aggregates.UserAdapterSoapAndRA;
import pl.lodz.p.it.SoapModel.AdminSoap;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.ManagerSoap;
import pl.lodz.p.it.SoapModel.UserSoap;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class SoapUserService  {

    @Inject
    UserAdapterSoapAndRA userAdapter;


//    @WebMethod
//    public Map<String, UserSoap> getUsers() {
//        return userAdapter.getUsers();
//    }

//    @WebMethod
//    public HashMap<String, UserSoap> getFilteredUsers(String input) {
//        return (HashMap<String, UserSoap>) userAdapter.getFilteredUsers(input);
//    }
//
//    @WebMethod
//    public Map<String, ClientSoap> getClients() {
//        return userAdapter.getClients();
//    }
//
//    @WebMethod
//    public UserSoap getUser(String login) {
//        return userAdapter.getUser(login);
//    }

    @WebMethod
    public void addClient(ClientSoap client) {
        userAdapter.addClient(client);
    }

    @WebMethod
    public void addAdmin(AdminSoap admin) {
        userAdapter.addAdmin(admin);
    }

    @WebMethod
    public void addManager(ManagerSoap manager) {
        userAdapter.addManager(manager);
    }

    @WebMethod
    public void updateUser(UserSoap user) {
        userAdapter.updateUser(user, user.getPassword(), user.getName(), user.getSurname());
    }
}

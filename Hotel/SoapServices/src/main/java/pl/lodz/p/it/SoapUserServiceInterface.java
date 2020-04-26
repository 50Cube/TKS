package pl.lodz.p.it;

import pl.lodz.p.it.SoapModel.AdminSoap;
import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.ManagerSoap;
import pl.lodz.p.it.SoapModel.UserSoap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Map;


@WebService
public interface SoapUserServiceInterface {
    @WebMethod
    public Map<String, UserSoap> getUsers();

    @WebMethod
    public Map<String, UserSoap> getFilteredUsers(String input);

    @WebMethod
    public Map<String, ClientSoap> getClients();

    @WebMethod
    public UserSoap getUser(String login);

    @WebMethod
    public void addClient(ClientSoap client);

    @WebMethod
    public void addAdmin(AdminSoap admin);

    @WebMethod
    public void addManager(ManagerSoap manager);

    @WebMethod
    public void updateUser(UserSoap user);
}

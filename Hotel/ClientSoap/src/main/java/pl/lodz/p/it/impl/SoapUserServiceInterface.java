
package pl.lodz.p.it.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import pl.lodz.p.it.AdminSoap;
import pl.lodz.p.it.ClientSoap;
import pl.lodz.p.it.ManagerSoap;
import pl.lodz.p.it.ObjectFactory;
import pl.lodz.p.it.UserSoap;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SoapUserServiceInterface", targetNamespace = "http://it.p.lodz.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SoapUserServiceInterface {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addManager", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddManager")
    @ResponseWrapper(localName = "addManagerResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddManagerResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/addManagerRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/addManagerResponse")
    public void addManager(
        @WebParam(name = "arg0", targetNamespace = "")
        ManagerSoap arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addClient", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddClient")
    @ResponseWrapper(localName = "addClientResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddClientResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/addClientRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/addClientResponse")
    public void addClient(
        @WebParam(name = "arg0", targetNamespace = "")
        ClientSoap arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addAdmin", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddAdmin")
    @ResponseWrapper(localName = "addAdminResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.AddAdminResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/addAdminRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/addAdminResponse")
    public void addAdmin(
        @WebParam(name = "arg0", targetNamespace = "")
        AdminSoap arg0);

    /**
     * 
     * @return
     *     returns java.util.List<pl.lodz.p.it.UserSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUsers", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetUsers")
    @ResponseWrapper(localName = "getUsersResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetUsersResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/getUsersRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/getUsersResponse")
    public List<UserSoap> getUsers();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<pl.lodz.p.it.UserSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFilteredUsers", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetFilteredUsers")
    @ResponseWrapper(localName = "getFilteredUsersResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetFilteredUsersResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/getFilteredUsersRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/getFilteredUsersResponse")
    public List<UserSoap> getFilteredUsers(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns pl.lodz.p.it.UserSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetUserResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/getUserRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/getUserResponse")
    public UserSoap getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.UpdateUserResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/updateUserRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/updateUserResponse")
    public void updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        UserSoap arg0);

    /**
     * 
     * @return
     *     returns java.util.List<pl.lodz.p.it.ClientSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getClients", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetClients")
    @ResponseWrapper(localName = "getClientsResponse", targetNamespace = "http://it.p.lodz.pl/", className = "pl.lodz.p.it.GetClientsResponse")
    @Action(input = "http://it.p.lodz.pl/SoapUserServiceInterface/getClientsRequest", output = "http://it.p.lodz.pl/SoapUserServiceInterface/getClientsResponse")
    public List<ClientSoap> getClients();

}

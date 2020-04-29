
package pl.lodz.p.it;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.lodz.p.it package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUsersResponse_QNAME = new QName("http://it.p.lodz.pl/", "getUsersResponse");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://it.p.lodz.pl/", "updateUserResponse");
    private final static QName _GetUsers_QNAME = new QName("http://it.p.lodz.pl/", "getUsers");
    private final static QName _AddClientResponse_QNAME = new QName("http://it.p.lodz.pl/", "addClientResponse");
    private final static QName _AddAdminResponse_QNAME = new QName("http://it.p.lodz.pl/", "addAdminResponse");
    private final static QName _GetClientsResponse_QNAME = new QName("http://it.p.lodz.pl/", "getClientsResponse");
    private final static QName _GetFilteredUsers_QNAME = new QName("http://it.p.lodz.pl/", "getFilteredUsers");
    private final static QName _UserSoap_QNAME = new QName("http://it.p.lodz.pl/", "userSoap");
    private final static QName _AddManager_QNAME = new QName("http://it.p.lodz.pl/", "addManager");
    private final static QName _GetClients_QNAME = new QName("http://it.p.lodz.pl/", "getClients");
    private final static QName _UpdateUser_QNAME = new QName("http://it.p.lodz.pl/", "updateUser");
    private final static QName _GetFilteredUsersResponse_QNAME = new QName("http://it.p.lodz.pl/", "getFilteredUsersResponse");
    private final static QName _GetUser_QNAME = new QName("http://it.p.lodz.pl/", "getUser");
    private final static QName _AddAdmin_QNAME = new QName("http://it.p.lodz.pl/", "addAdmin");
    private final static QName _AddClient_QNAME = new QName("http://it.p.lodz.pl/", "addClient");
    private final static QName _AddManagerResponse_QNAME = new QName("http://it.p.lodz.pl/", "addManagerResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://it.p.lodz.pl/", "getUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.lodz.p.it
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link AddManagerResponse }
     * 
     */
    public AddManagerResponse createAddManagerResponse() {
        return new AddManagerResponse();
    }

    /**
     * Create an instance of {@link AddAdmin }
     * 
     */
    public AddAdmin createAddAdmin() {
        return new AddAdmin();
    }

    /**
     * Create an instance of {@link AddClient }
     * 
     */
    public AddClient createAddClient() {
        return new AddClient();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link GetFilteredUsersResponse }
     * 
     */
    public GetFilteredUsersResponse createGetFilteredUsersResponse() {
        return new GetFilteredUsersResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link AddManager }
     * 
     */
    public AddManager createAddManager() {
        return new AddManager();
    }

    /**
     * Create an instance of {@link GetClients }
     * 
     */
    public GetClients createGetClients() {
        return new GetClients();
    }

    /**
     * Create an instance of {@link GetFilteredUsers }
     * 
     */
    public GetFilteredUsers createGetFilteredUsers() {
        return new GetFilteredUsers();
    }

    /**
     * Create an instance of {@link GetClientsResponse }
     * 
     */
    public GetClientsResponse createGetClientsResponse() {
        return new GetClientsResponse();
    }

    /**
     * Create an instance of {@link AddAdminResponse }
     * 
     */
    public AddAdminResponse createAddAdminResponse() {
        return new AddAdminResponse();
    }

    /**
     * Create an instance of {@link AddClientResponse }
     * 
     */
    public AddClientResponse createAddClientResponse() {
        return new AddClientResponse();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link ClientSoap }
     * 
     */
    public ClientSoap createClientSoap() {
        return new ClientSoap();
    }

    /**
     * Create an instance of {@link ManagerSoap }
     * 
     */
    public ManagerSoap createManagerSoap() {
        return new ManagerSoap();
    }

    /**
     * Create an instance of {@link AdminSoap }
     * 
     */
    public AdminSoap createAdminSoap() {
        return new AdminSoap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addClientResponse")
    public JAXBElement<AddClientResponse> createAddClientResponse(AddClientResponse value) {
        return new JAXBElement<AddClientResponse>(_AddClientResponse_QNAME, AddClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addAdminResponse")
    public JAXBElement<AddAdminResponse> createAddAdminResponse(AddAdminResponse value) {
        return new JAXBElement<AddAdminResponse>(_AddAdminResponse_QNAME, AddAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getClientsResponse")
    public JAXBElement<GetClientsResponse> createGetClientsResponse(GetClientsResponse value) {
        return new JAXBElement<GetClientsResponse>(_GetClientsResponse_QNAME, GetClientsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilteredUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getFilteredUsers")
    public JAXBElement<GetFilteredUsers> createGetFilteredUsers(GetFilteredUsers value) {
        return new JAXBElement<GetFilteredUsers>(_GetFilteredUsers_QNAME, GetFilteredUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSoap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "userSoap")
    public JAXBElement<UserSoap> createUserSoap(UserSoap value) {
        return new JAXBElement<UserSoap>(_UserSoap_QNAME, UserSoap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddManager }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addManager")
    public JAXBElement<AddManager> createAddManager(AddManager value) {
        return new JAXBElement<AddManager>(_AddManager_QNAME, AddManager.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClients }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getClients")
    public JAXBElement<GetClients> createGetClients(GetClients value) {
        return new JAXBElement<GetClients>(_GetClients_QNAME, GetClients.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilteredUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getFilteredUsersResponse")
    public JAXBElement<GetFilteredUsersResponse> createGetFilteredUsersResponse(GetFilteredUsersResponse value) {
        return new JAXBElement<GetFilteredUsersResponse>(_GetFilteredUsersResponse_QNAME, GetFilteredUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addAdmin")
    public JAXBElement<AddAdmin> createAddAdmin(AddAdmin value) {
        return new JAXBElement<AddAdmin>(_AddAdmin_QNAME, AddAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addClient")
    public JAXBElement<AddClient> createAddClient(AddClient value) {
        return new JAXBElement<AddClient>(_AddClient_QNAME, AddClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddManagerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "addManagerResponse")
    public JAXBElement<AddManagerResponse> createAddManagerResponse(AddManagerResponse value) {
        return new JAXBElement<AddManagerResponse>(_AddManagerResponse_QNAME, AddManagerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://it.p.lodz.pl/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

}

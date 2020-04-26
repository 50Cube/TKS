//package pl.lodz.p.it.Aggregates;
//
//import pl.lodz.p.it.Converters.UserConverterUIAndSoap;
//import pl.lodz.p.it.Ports.UI.AddUserPort;
//import pl.lodz.p.it.Ports.UI.ChangeUserActivenessPort;
//import pl.lodz.p.it.Ports.UI.GetUserPort;
//import pl.lodz.p.it.Ports.UI.UpdateUserPort;
//import pl.lodz.p.it.SoapModel.AdminSoap;
//import pl.lodz.p.it.SoapModel.ClientSoap;
//import pl.lodz.p.it.SoapModel.ManagerSoap;
//import pl.lodz.p.it.UIModel.AdminUI;
//import pl.lodz.p.it.UIModel.ClientUI;
//import pl.lodz.p.it.UIModel.ManagerUI;
//import pl.lodz.p.it.UIModel.UserUI;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.util.Map;
//
//@ApplicationScoped
//@Named
//public class UserAdapterUIAndSoap implements AddUserPort, ChangeUserActivenessPort, GetUserPort, UpdateUserPort {
//
//    @Inject
//    UserAdapterSoapAndRA userAdapter;
//
//    @Inject
//    UserConverterUIAndSoap converter;
//
//    @Override
//    public void addClient(String login, String password, String name, String surname, boolean active) {
//        userAdapter.addClient((ClientSoap) converter.convertToSoap(new UserUI(login, password, name, surname, active) {
//            @Override
//            public String getType() {
//                return "Client";
//            }
//        }));
//    }
//
//    @Override
//    public void addManager(String login, String password, String name, String surname, boolean active) {
//        userAdapter.addManager((ManagerSoap) converter.convertToSoap(new ManagerUI(login, password, name, surname, active)));
//    }
//
//    @Override
//    public void addAdmin(String login, String password, String name, String surname, boolean active) {
//        userAdapter.addAdmin((AdminSoap) converter.convertToSoap(new AdminUI(login, password, name, surname, active)));
//    }
//
//    @Override
//    public void activateUser(String login) {
//        userAdapter.activateUser(userAdapter.getUser(login));
//    }
//
//    @Override
//    public void deactivateUser(String login) {
//        userAdapter.deactivateUser(userAdapter.getUser(login));
//    }
//
//    @Override
//    public UserUI getUser(String login) {
//        return converter.convertToUI(userAdapter.getUser(login));
//    }
//
//    @Override
//    public Map<String, UserUI> getUsers() {
//        return converter.convertUserMapToUI(userAdapter.getUsers());
//    }
//
//    @Override
//    public Map<String, ClientUI> getClients() {
//        return converter.convertClientMapToUI(userAdapter.getClients());
//    }
//
//    @Override
//    public Map<String, UserUI> getFilterUsers(String input) {
//        return converter.convertUserMapToUI(userAdapter.getFilteredUsers(input));
//    }
//
//    @Override
//    public void updateUser(String login, String newPassword, String newName, String newSurname) {
//        userAdapter.updateUser(userAdapter.getUser(login), newPassword, newName, newSurname);
//    }
//}

//package pl.lodz.p.it.Converters;
//
//import pl.lodz.p.it.SoapModel.AdminSoap;
//import pl.lodz.p.it.SoapModel.ClientSoap;
//import pl.lodz.p.it.SoapModel.ManagerSoap;
//import pl.lodz.p.it.SoapModel.UserSoap;
//import pl.lodz.p.it.UIModel.AdminUI;
//import pl.lodz.p.it.UIModel.ClientUI;
//import pl.lodz.p.it.UIModel.ManagerUI;
//import pl.lodz.p.it.UIModel.UserUI;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Named;
//import java.util.HashMap;
//import java.util.Map;
//
//@ApplicationScoped
//@Named
//public class UserConverterUIAndSoap {
//    public UserSoap convertToSoap(UserUI user){
//        if (user instanceof AdminUI) {
//            return new AdminSoap(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//
//        if (user instanceof ManagerUI) {
//            return new ManagerSoap(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//
//        if (user instanceof ClientUI) {
//            return new ClientSoap(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//        return null;
//    }
//
//    public UserUI convertToUI(UserSoap user){
//        if (user instanceof AdminSoap) {
//            return new AdminUI(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//
//        if (user instanceof ManagerSoap) {
//            return new ManagerUI(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//
//        if (user instanceof ClientSoap) {
//            return new ClientUI(
//                    user.getLogin(),
//                    user.getPassword(),
//                    user.getName(),
//                    user.getSurname(),
//                    user.getIsActive()
//            );
//        }
//        return null;
//    }
//
//    public Map<String, UserUI> convertUserMapToUI(Map<String, UserSoap> map) {
//        Map<String, UserUI> userUIMap = new HashMap<>();
//        for (Map.Entry<String, UserSoap> entry : map.entrySet()
//        ) {
//            UserUI userUI = convertToUI(entry.getValue());
//            userUIMap.put(entry.getKey(), userUI);
//        }
//
//        return userUIMap;
//    }
//
//    public Map<String, ClientUI> convertClientMapToUI(Map<String, ClientSoap> map) {
//        Map<String, ClientUI> clientUIMap = new HashMap<>();
//        for (Map.Entry<String, ClientSoap> entry : map.entrySet()
//        ) {
//            ClientUI clientUI = (ClientUI) convertToUI(entry.getValue());
//            clientUIMap.put(entry.getKey(), clientUI);
//        }
//
//        return clientUIMap;
//    }
//}

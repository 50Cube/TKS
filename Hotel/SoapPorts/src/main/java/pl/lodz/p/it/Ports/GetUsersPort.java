package pl.lodz.p.it.Ports;

import pl.lodz.p.it.SoapModel.ClientSoap;
import pl.lodz.p.it.SoapModel.UserSoap;

import java.util.Map;

public interface GetUsersPort {
    Map<String, UserSoap> getUsers();
    Map<String, ClientSoap> getClients();
}

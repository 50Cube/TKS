package pl.lodz.p.it.Ports;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface AddUserPort {
    void addUser(UserSoap user);
}

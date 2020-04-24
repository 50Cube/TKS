package pl.lodz.p.it.Ports.Soap;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface AddUserPort {
    void addUser(UserSoap user);
}

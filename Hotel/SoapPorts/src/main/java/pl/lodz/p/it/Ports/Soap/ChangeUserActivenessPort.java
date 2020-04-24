package pl.lodz.p.it.Ports.Soap;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface ChangeUserActivenessPort {
    void activateUser(UserSoap user);
    void deactivateUser(UserSoap user);
}

package pl.lodz.p.it.Ports;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface UpdateUserPort {
    void updateUser(UserSoap user, String newName, String newSurname);
}

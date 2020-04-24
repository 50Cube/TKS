package pl.lodz.p.it.Ports.Soap;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface UpdateUserPort {
    void updateUser(UserSoap user, String newPassword, String newName, String newSurname);
}

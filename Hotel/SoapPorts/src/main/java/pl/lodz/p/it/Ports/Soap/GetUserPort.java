package pl.lodz.p.it.Ports.Soap;

import pl.lodz.p.it.SoapModel.UserSoap;

public interface GetUserPort {
    UserSoap getUser(String login);
}

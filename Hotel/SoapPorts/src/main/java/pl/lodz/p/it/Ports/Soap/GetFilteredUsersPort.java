package pl.lodz.p.it.Ports.Soap;

import pl.lodz.p.it.SoapModel.UserSoap;

import java.util.Map;

public interface GetFilteredUsersPort {
    Map<String, UserSoap> getFilteredUsers(String input);
}

package pl.lodz.p.it.SoapModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AdminSoap extends UserSoap
{
    public AdminSoap(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}

package pl.lodz.p.it.SoapModel;


public class AdminSoap extends UserSoap
{
    public AdminSoap() {}

    public AdminSoap(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}

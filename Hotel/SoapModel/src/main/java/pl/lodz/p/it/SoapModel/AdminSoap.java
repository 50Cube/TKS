package pl.lodz.p.it.SoapModel;


public class AdminSoap extends UserSoap
{
    public AdminSoap() {}

    public AdminSoap(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}

package pl.lodz.p.it.SoapModel;


public class ManagerSoap extends UserSoap
{
    public ManagerSoap() {}

    public ManagerSoap(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

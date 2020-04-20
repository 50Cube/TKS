package pl.lodz.p.it.RestModel;

public class ManagerRest extends UserRest
{
    public ManagerRest(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

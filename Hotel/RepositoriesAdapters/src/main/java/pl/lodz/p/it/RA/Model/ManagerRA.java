package pl.lodz.p.it.RA.Model;

public class ManagerRA extends UserRA
{
    public ManagerRA(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

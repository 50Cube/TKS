package pl.lodz.p.it.RA.Model;

public class AdminRA extends UserRA
{
    public AdminRA(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}

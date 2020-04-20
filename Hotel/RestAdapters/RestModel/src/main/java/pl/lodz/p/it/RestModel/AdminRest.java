package pl.lodz.p.it.RestModel;

public class AdminRest extends UserRest
{
    public AdminRest(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}


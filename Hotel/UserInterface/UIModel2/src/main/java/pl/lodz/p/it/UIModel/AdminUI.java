package pl.lodz.p.it.UIModel;

public class AdminUI extends UserUI
{
    public AdminUI(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}


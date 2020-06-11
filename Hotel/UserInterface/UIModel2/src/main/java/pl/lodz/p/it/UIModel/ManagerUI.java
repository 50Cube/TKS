package pl.lodz.p.it.UIModel;

public class ManagerUI extends UserUI
{
    public ManagerUI(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

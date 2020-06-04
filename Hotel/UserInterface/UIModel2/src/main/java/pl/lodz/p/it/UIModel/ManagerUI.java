package pl.lodz.p.it.UIModel;

public class ManagerUI extends UserUI
{
    public ManagerUI(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

package pl.lodz.p.it.UIModel;

public class ClientUI extends UserUI
{
    public ClientUI(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
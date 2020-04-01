package Model;

public class ClientUI extends UserUI
{
    public ClientUI(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
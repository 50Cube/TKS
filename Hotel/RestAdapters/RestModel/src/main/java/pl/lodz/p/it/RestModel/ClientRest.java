package pl.lodz.p.it.RestModel;

public class ClientRest extends UserRest
{
    public ClientRest(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
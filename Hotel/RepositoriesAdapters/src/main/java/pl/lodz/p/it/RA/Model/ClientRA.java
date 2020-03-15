package pl.lodz.p.it.RA.Model;

public class ClientRA extends UserRA
{
    public ClientRA(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
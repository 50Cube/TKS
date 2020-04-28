package pl.lodz.p.it.SoapModel;


public class ClientSoap extends UserSoap
{
    public ClientSoap() {}

    public ClientSoap(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
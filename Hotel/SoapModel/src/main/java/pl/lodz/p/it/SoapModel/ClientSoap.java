package pl.lodz.p.it.SoapModel;


public class ClientSoap extends UserSoap
{
    public ClientSoap() {}

    public ClientSoap(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
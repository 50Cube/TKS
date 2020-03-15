package com.mycompany.store.Model;

public class Client extends User
{
    public Client(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }  
    
    @Override
    public String getType() {
        return "Client";
    }
}
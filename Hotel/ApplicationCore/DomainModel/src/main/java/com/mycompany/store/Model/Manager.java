package com.mycompany.store.Model;

public class Manager extends User
{
    public Manager(String login, String password, String name, String surname, boolean active) {
        super(login, password, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

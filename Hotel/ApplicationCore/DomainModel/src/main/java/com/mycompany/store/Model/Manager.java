package com.mycompany.store.Model;

public class Manager extends User
{
    public Manager(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Manager";
    }
}

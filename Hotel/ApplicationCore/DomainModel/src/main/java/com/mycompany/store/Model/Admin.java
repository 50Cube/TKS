package com.mycompany.store.Model;

public class Admin extends User
{
    public Admin(String login, String name, String surname, boolean active) {
        super(login, name, surname, active);
    }
    
    @Override
    public String getType() {
        return "Admin";
    }
}

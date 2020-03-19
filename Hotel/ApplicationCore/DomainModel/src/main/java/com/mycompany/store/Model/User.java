package com.mycompany.store.Model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public abstract class User {
    @NotBlank
    private String login;
    private String password;
    private String name;
    private String surname;
    private Boolean isActive;
    
    public User(String login, String password, String name, String surname, boolean active) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isActive = active;
    }
    
    public abstract String getType();
            
    @Override
    public String toString()
    {
        return "User details:\nName: " + this.getName() + "\nSurname: " + this.getSurname() + "\nLogin: " + this.getLogin() + "\nActive: " + this.getIsActive();
    }
    
    public String toFilterString() {
        return this.login + " " + this.name + " " + this.surname;
    }
    
    public String activeString() {
        if(this.isActive) return "Active";
        else return "Inactive";
    }
}

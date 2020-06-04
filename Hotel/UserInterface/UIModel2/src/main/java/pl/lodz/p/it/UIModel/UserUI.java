package pl.lodz.p.it.UIModel;

import lombok.Data;

@Data
public abstract class UserUI {
    private String login;
    private String name;
    private String surname;
    private Boolean isActive;
    
    public UserUI(String login, String name, String surname, boolean active) {
        this.name = name;
        this.surname = surname;
        this.login = login;
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

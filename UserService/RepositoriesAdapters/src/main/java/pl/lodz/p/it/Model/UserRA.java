package pl.lodz.p.it.Model;

import lombok.Data;

@Data
public class UserRA {
    private String login;
    private String password;
    private String name;
    private String surname;
    private Boolean isActive;
    private Group group;

    public UserRA() {}

    public UserRA(String login, String password, String name, String surname, Boolean isActive, String group) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
        this.group = Group.valueOf(group.toUpperCase());
    }

    public String toFilterString() {
        return this.login + " " + this.name + " " + this.surname;
    }

    public String activeString() {
        if(this.isActive) return "Active";
        else return "Inactive";
    }

    public enum Group{
        MANAGER,
        ADMIN,
        CLIENT
    }
}

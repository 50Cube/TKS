package pl.lodz.p.it;

import lombok.Data;

@Data
public class User {
    private String login;
    private String password;
    private String name;
    private String surname;
    private Boolean isActive;
    private String group;

    public User() {}

    public User(String login, String password, String name, String surname, Boolean isActive, String group) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
        this.group = group;
    }
}

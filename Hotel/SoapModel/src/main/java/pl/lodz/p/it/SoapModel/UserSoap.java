package pl.lodz.p.it.SoapModel;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class UserSoap {
    @XmlElement
    private String login;
    @XmlElement
    private String password;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private Boolean isActive;
    
    public UserSoap(String login, String password, String name, String surname, boolean active) {
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

package pl.lodz.p.it.SoapModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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

    public UserSoap() {}

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
        return "User details:\nName: ".concat(this.getName()).concat("\nSurname: ").concat(this.getSurname()).concat("\nLogin: ").concat(this.getLogin()).concat("\nActive: ").concat(this.getIsActive().toString());
    }
    
    public String toFilterString() {
        return this.login.concat(this.name).concat(this.surname);
    }
    
    public String activeString() {
        if(this.isActive) return "Active";
        else return "Inactive";
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserSoap)) return false;
        final UserSoap other = (UserSoap) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$login = this.getLogin();
        final Object other$login = other.getLogin();
        if (this$login == null ? other$login != null : !this$login.equals(other$login)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$surname = this.getSurname();
        final Object other$surname = other.getSurname();
        if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
        final Object this$isActive = this.getIsActive();
        final Object other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !this$isActive.equals(other$isActive)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserSoap;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $login = this.getLogin();
        result = result * PRIME + ($login == null ? 43 : $login.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $surname = this.getSurname();
        result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
        final Object $isActive = this.getIsActive();
        result = result * PRIME + ($isActive == null ? 43 : $isActive.hashCode());
        return result;
    }
}

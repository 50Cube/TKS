package UIControllers;

import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import pl.lodz.p.it.Publisher;

import java.io.Serializable;
import java.util.concurrent.TimeoutException;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;

@Named(value = "addUserController")
@ConversationScoped
@Log
public class addUserController implements Serializable{

    private String userType;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String activeString = "false";
    private boolean isActive;
    
    @Inject
    private Conversation conversation;

    @Inject
    private Publisher publisher;
    
    public addUserController() { }
    
    public void setUserType(String type) {
        this.userType = type;
    }
    
    public String getUserType() {
        return this.userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getActiveString() {
        return activeString;
    }
    
    public void setActiveString(String active) {
        this.activeString = active;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(){
        if(this.activeString.equals("true"))
            this.isActive = true;
        else this.isActive = false;
    }
    public String register() throws Exception
    {
        String gRecaptchaResponse = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
               boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
        if(!verify){
            FacesContext context = FacesContext.getCurrentInstance();
                  context.addMessage( null, new FacesMessage( "Stop being a robot") );
                   return null;
        }
        else{
                if(!conversation.isTransient()){
                    conversation.end();
                }
            conversation.begin();
            this.setIsActive();
            this.setUserType("Client");      
            addUserConfirm();
            return "home";
        } 
    }
    public String addUser() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        return "addUser";
    }
    
    public String addUserConfirm() {
        this.setIsActive();
        try {
            String json = Json.createObjectBuilder()
                    .add("userType", userType.toUpperCase())
                    .add("login", login)
                    .add("name", name)
                    .add("surname", surname)
                    .add("isActive", isActive)
                    .add("password", DigestUtils.sha256Hex(password)).build().toString();
            publisher.createUser(json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        conversation.end();
        return "home";
    }
}

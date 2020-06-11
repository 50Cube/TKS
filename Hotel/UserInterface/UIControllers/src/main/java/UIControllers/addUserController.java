package UIControllers;

import pl.lodz.p.it.Publisher;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;


@Named(value = "addUserController")
@ConversationScoped
public class addUserController implements Serializable{

    private String userType;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String activeString = "false";
    private boolean isActive;
    
    @Inject
    private UserAdapterUI userService;
    
    @Inject
    private Conversation conversation;

    @Inject
    private Publisher publisher;
    
    public addUserController() {
    }
    
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
    
    public String addUserConfirm() throws Exception {
        this.setIsActive();
        if (userType.equals("Client"))
            userService.addClient(login, name, surname, isActive);
        else if (userType.equals("Manager"))
            userService.addManager(login, name, surname, isActive);
        else if (userType.equals("Admin"))
            userService.addAdmin(login, name, surname, isActive);

        String json = Json.createObjectBuilder()
                .add("userType", userType)
                .add("login", login)
                .add("name", name)
                .add("surname", surname)
                .add("isActive", isActive)
                .add("password", password).build().toString();
        publisher.createUser(json);
        conversation.end();
        return "home";
    }
}

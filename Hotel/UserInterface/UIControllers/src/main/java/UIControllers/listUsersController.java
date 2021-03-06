package UIControllers;

import pl.lodz.p.it.Publisher;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;
import pl.lodz.p.it.UIModel.UserUI;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listUsersController")
@ViewScoped
public class listUsersController implements Serializable{

    @Inject
    private UserAdapterUI userService;
    
    @Inject
    private DataHolder dh;

    @Inject
    private Publisher publisher;

    private Map<String, UserUI> users;
    private String filter;
    
    public listUsersController() {
    }
    
    @PostConstruct
    public void loadUsers()
    {
        try {
            users = publisher.getUsers("getAll.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, UserUI> getUsers()
    {
        return users;
    }
    
    public UserUI getUser(String name)
    {
        return this.userService.getUser(name);
    }
    
    public String saveData(UserUI user) {
        dh.setUser(user);
        return "updateUser.xhtml";
    }
    
    public void getFilteredUsers() {
        try {
            users = publisher.getUsers("getFiltered." + this.filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getFilter() {
        return this.filter;
    }
    
    public void setFilter(String filter) {
        this.filter = filter;
    }
    
    public void activate(UserUI user) {
        if(user.getIsActive())
            userService.deactivateUser(user.getLogin());
        else userService.activateUser(user.getLogin());
        loadUsers();
    }
}

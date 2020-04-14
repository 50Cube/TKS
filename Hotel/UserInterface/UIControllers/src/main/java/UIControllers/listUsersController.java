package UIControllers;

import com.mycompany.store.Services.UserService;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;
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
    private UserService userService;
    
    @Inject
    private DataHolder dh;

    @Inject
    private UserConverterUI converterUI;

    private Map<String, UserUI> users;
    private String filter;
    
    public listUsersController() {
    }
    
    @PostConstruct
    public void loadUsers()
    {
        users = converterUI.convertUserMapToUI(userService.getUsers());
    }
    
    public Map<String, UserUI> getUsers()
    {
        return users;
    }
    
    public UserUI getUser(String name)
    {
        return converterUI.convertToUI(this.userService.getUser(name));
    }
    
    public String saveData(UserUI user) {
        dh.setUser(user);
        return "updateUser.xhtml";
    }
    
    public void getFilteredUsers() {
        users = converterUI.convertUserMapToUI(userService.getFilterUsers(this.filter));
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

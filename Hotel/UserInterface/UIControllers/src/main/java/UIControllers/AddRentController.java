package UIControllers;


import com.mycompany.store.Services.RentService;
import com.mycompany.store.Services.UserService;
import pl.lodz.p.it.UIPorts.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIPorts.Converters.UI.UserConverterUI;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.RentUI;
import pl.lodz.p.it.UIModel.RentableUI;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addRentController")
@ConversationScoped
public class AddRentController implements Serializable {
    @Inject
    private RentService rentService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private Conversation conversation;

    @Inject
    private UserConverterUI converterUI;

    @Inject
    private RentableConverterUI rentableConverterUI;

    private RentUI rent;
    private Date start;
    private Date stop;
    private Map<String, ClientUI> clients;
    private String clientLogin;
    
    public AddRentController() {
    }
    
    @PostConstruct
    private void init() {
        rent = new RentUI();
        clients = converterUI.convertClientMapToUI(userService.getClients());
    }
    
    public RentUI getRent() {
        return rent;
    }
    
    public String addRent(RentableUI rentable) {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        
        rent.setRentable(rentable);
        
        return "addRent";
    }
    
    public String addRentConfirm() {
        Calendar tmp1 = new GregorianCalendar();
        Calendar tmp2 = new GregorianCalendar();
        tmp1.setTime(start);
        tmp2.setTime(stop);
        chooseClient();
        
        if(stop.before(start)) throw new IllegalArgumentException("Beginning date must be earlier than end date");
        if(!rentService.checkIfRented(tmp1,tmp2)) throw new IllegalArgumentException("Room is already rent");
        
        rentService.addRent(rentableConverterUI.convertToDomain(rent.getRentable()), converterUI.convertClientToDomain((rent.getClient())), tmp1, tmp2);
        conversation.end();
        return "home";
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public void setStart(Date start) {
        this.start = start;
    }
    
    public Date getStop() {
        return this.stop;
    }
    
    public void setStop(Date stop) {
        this.stop = stop;
    }
    
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
    
    public Map<String, ClientUI> getClients() {
        return this.clients;
    }
    
    public String getClientLogin() {
        return this.clientLogin;
    }
    
    public void setClientLogin(String string) {
        this.clientLogin = string;
    }
    
    private void chooseClient() {
        if(userService.getUser(clientLogin) != null)
            if(converterUI.convertToUI(userService.getUser(clientLogin)) instanceof ClientUI)
                if(userService.getUser(clientLogin).getIsActive())
                    rent.setClient((ClientUI) converterUI.convertToUI(userService.getUser(clientLogin)));
                else throw new IllegalArgumentException("Client is inactive");
            else throw new IllegalArgumentException("Only clients can rent rooms");
        else throw new IllegalArgumentException("Client with this login does not exists");
    }
}

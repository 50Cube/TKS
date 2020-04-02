package UIControllers;


import com.mycompany.store.Services.RentableService;
import pl.lodz.p.it.Converters.UI.RentableConverterUI;
import pl.lodz.p.it.UIModel.RoomUI;
import pl.lodz.p.it.UIModel.SaunaUI;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "addRentableController")
@ConversationScoped
public class addRentableController implements Serializable{

    @Inject
    private RentableService rentableService;
    
    @Inject
    private Conversation conversation;

    @Inject
    private RentableConverterUI converterUI;
    
    private RoomUI room;
    private SaunaUI sauna;
    
    private String number;
    private String area;
    private String beds;
    
    private String price;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
    public addRentableController() {
    }
    
    @PostConstruct
    private void init()
    {
        room = new RoomUI();
        sauna = new SaunaUI();
    }
    
    public RoomUI getRoom() {
        return room;
    }
    
    public SaunaUI getSauna() {
        return sauna;
    }
    
    public String addRoom() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        room.setNumber(Integer.parseInt(number));
        room.setArea(Double.parseDouble(area));
        room.setBeds(Integer.parseInt(beds));
        return "addRoom";
    }
    
    public String addRoomConfirm() {
    rentableService.addRentable(converterUI.convertToDomain(room));
        conversation.end();
        return "home";
    }
    
    public String addSauna() {
        if(!conversation.isTransient())
            conversation.end();
        conversation.begin();
        sauna.setNumber(Integer.parseInt(number));
        sauna.setPricePerHour(Double.parseDouble(price));
        return "addSauna";
    }
    
    public String addSaunaConfirm() {
        rentableService.addRentable(converterUI.convertToDomain(sauna));
        conversation.end();
        return "home";
    }
}

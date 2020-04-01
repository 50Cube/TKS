package Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoomUI extends RentableUI implements Serializable {

    private double area;


    private int beds;

    public RoomUI() {
        super();
    }
    
    public RoomUI(int number, double area, int beds) {
        super(number);
        this.area = area;
        this.beds = beds;
    }
    
    @Override
    public String toString()
    {
        return "Room no. " + this.getNumber() + " has " + this.area + " m2 and is designed for " + this.beds + " people.";
    }
}

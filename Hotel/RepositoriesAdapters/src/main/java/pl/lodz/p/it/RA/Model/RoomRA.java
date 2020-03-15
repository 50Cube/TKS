package pl.lodz.p.it.RA.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class RoomRA extends RentableRA implements Serializable {
    @NotNull
    @Positive
    private double area;
    
    @NotNull
    @Positive
    private int beds;

    public RoomRA () {
        super();
    }
    
    public RoomRA(int number, double area, int beds) {
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

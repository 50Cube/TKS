package pl.lodz.p.it.SoapModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoomSoap extends RentableSoap implements Serializable {

    private double area;


    private int beds;

    public RoomSoap() {
        super();
    }
    
    public RoomSoap(int number, double area, int beds) {
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

package pl.lodz.p.it.RestModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaunaRest extends RentableRest implements Serializable {

    private double pricePerHour;
    
    public SaunaRest() {
        super();
    }
    
    public SaunaRest(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

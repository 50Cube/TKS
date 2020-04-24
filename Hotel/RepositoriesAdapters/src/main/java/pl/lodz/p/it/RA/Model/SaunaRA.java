package pl.lodz.p.it.RA.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaunaRA extends RentableRA implements Serializable {

    private double pricePerHour;
    
    public SaunaRA() {
        super();
    }
    
    public SaunaRA(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

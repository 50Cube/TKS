package Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaunaUI extends RentableUI implements Serializable {

    private double pricePerHour;
    
    public SaunaUI() {
        super();
    }
    
    public SaunaUI(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

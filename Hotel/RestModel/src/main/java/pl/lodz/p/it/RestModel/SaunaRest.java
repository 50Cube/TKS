package pl.lodz.p.it.RestModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaunaRest extends RentableRest implements Serializable {
    @NotNull
    @Positive
    @JsonbProperty
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

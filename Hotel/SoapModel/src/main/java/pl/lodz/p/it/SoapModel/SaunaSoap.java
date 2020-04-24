package pl.lodz.p.it.SoapModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaunaSoap extends RentableSoap implements Serializable {

    private double pricePerHour;
    
    public SaunaSoap() {
        super();
    }
    
    public SaunaSoap(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

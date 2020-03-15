package com.mycompany.store.Model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Sauna extends Rentable implements Serializable {
    private double pricePerHour;
    
    public Sauna() {
        super();
    }
    
    public Sauna(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    public double getPricePerHour()
    {
        return this.pricePerHour;
    }
    
    public void setPricePerHour(double price)
    {
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

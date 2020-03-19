package com.mycompany.store.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode(callSuper = false)
public class Sauna extends Rentable implements Serializable {
    @NotNull
    @Positive
    private double pricePerHour;
    
    public Sauna() {
        super();
    }
    
    public Sauna(int number, double price) {
        super(number);
        this.pricePerHour = price;
    }
    
    @Override
    public String toString()
    {
        return "Sauna no. " + this.getNumber() + " costs " + this.pricePerHour + " per hour.";
    }
}

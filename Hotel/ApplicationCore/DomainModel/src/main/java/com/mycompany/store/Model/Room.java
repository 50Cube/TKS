package com.mycompany.store.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode(callSuper = false)
public class Room extends Rentable implements Serializable {
    @NotNull
    @Positive
    private double area;

    @NotNull
    @Positive
    private int beds;

    public Room () {
        super();
    }
    
    public Room(int number, double area, int beds) {
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

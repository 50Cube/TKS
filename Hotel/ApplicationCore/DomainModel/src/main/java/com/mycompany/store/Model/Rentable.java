package com.mycompany.store.Model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public abstract class Rentable implements Serializable {
    private int number;

    public Rentable () {}
    
    public Rentable(int number) {
        this.number = number;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
}
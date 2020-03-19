package com.mycompany.store.Model;

import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public abstract class Rentable implements Serializable {
    @NotNull
    @Positive
    private int number;

    public Rentable () {}
    
    public Rentable(int number) {
        this.number = number;
    }
}
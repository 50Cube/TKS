package pl.lodz.p.it.RestModel;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class RentableRest implements Serializable {
    private int number;

    public RentableRest() {}
    
    public RentableRest(int number) {
        this.number = number;
    }
}
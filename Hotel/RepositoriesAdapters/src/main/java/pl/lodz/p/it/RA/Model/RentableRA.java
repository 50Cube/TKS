package pl.lodz.p.it.RA.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class RentableRA implements Serializable {

    private int number;

    public RentableRA () {}
    
    public RentableRA(int number) {
        this.number = number;
    }
}
package pl.lodz.p.it.RA.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public abstract class RentableRA implements Serializable {
    @NotNull
    @Positive
    private int number;

    public RentableRA () {}
    
    public RentableRA(int number) {
        this.number = number;
    }
}
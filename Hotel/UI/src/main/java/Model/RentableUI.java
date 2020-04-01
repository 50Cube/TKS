package Model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public abstract class RentableUI implements Serializable {
    private int number;

    public RentableUI() {}
    
    public RentableUI(int number) {
        this.number = number;
    }
}
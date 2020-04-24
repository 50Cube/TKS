package pl.lodz.p.it.RestModel;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public abstract class RentableRest implements Serializable {
    @NotNull
    @Positive
    @JsonbProperty
    private int number;

    public RentableRest() {}
    
    public RentableRest(int number) {
        this.number = number;
    }
}
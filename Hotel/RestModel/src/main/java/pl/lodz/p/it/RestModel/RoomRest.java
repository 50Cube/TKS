package pl.lodz.p.it.RestModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoomRest extends RentableRest implements Serializable {
    @NotNull
    @Positive
    @JsonbProperty
    private double area;

    @NotNull
    @Positive
    @JsonbProperty
    private int beds;

    public RoomRest() {
        super();
    }
    
    public RoomRest(int number, double area, int beds) {
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

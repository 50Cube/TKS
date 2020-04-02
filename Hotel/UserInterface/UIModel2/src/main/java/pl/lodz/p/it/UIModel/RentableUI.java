package pl.lodz.p.it.UIModel;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class RentableUI implements Serializable {
    private int number;

    public RentableUI() {}
    
    public RentableUI(int number) {
        this.number = number;
    }
}
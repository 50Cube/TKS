package pl.lodz.p.it.SoapModel;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class RentableSoap implements Serializable {
    private int number;

    public RentableSoap() {}
    
    public RentableSoap(int number) {
        this.number = number;
    }
}
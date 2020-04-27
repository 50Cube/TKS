package pl.lodz.p.it.CustomExceptions;

import javax.ws.rs.WebApplicationException;

public class RentableRentedException extends WebApplicationException{
  
    public RentableRentedException(String errorMessage) {
        super(errorMessage);
    }
}

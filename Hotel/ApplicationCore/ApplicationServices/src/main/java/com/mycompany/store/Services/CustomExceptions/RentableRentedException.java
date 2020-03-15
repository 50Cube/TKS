package com.mycompany.store.Services.CustomExceptions;

import javax.ws.rs.WebApplicationException;

public class RentableRentedException extends WebApplicationException{
  
    public RentableRentedException(String errorMessage) {
        super(errorMessage);
    }
}

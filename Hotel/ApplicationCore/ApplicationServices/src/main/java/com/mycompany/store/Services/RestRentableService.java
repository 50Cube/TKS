package com.mycompany.store.Services;

import com.mycompany.store.Model.Rentable;
import com.mycompany.store.Model.Room;
import com.mycompany.store.Model.Sauna;
import com.mycompany.store.Repositories.RentRepository;
import com.mycompany.store.Repositories.RentableRepository;
import com.mycompany.store.Services.CustomExceptions.RentableRentedException;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named(value = "restRentableService")
@RequestScoped
@Path("model")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRentableService {
    
    @Inject
    private RentableRepository rentableRepository;
    
    @Inject
    private RentRepository rentRepository;

    public RestRentableService() {
    }
    
    @GET
    @Path("/rentables")
    public Response getRentables() {
        Map<Integer, Rentable> rentables = rentableRepository.getRentables();
        if(rentables != null){
            return Response.ok(rentables).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rooms")
    public Response getRooms() {
        Map<Integer, Room> rooms = rentableRepository.getRooms();
        if(rooms != null){
            return Response.ok(rooms).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("rooms/{filter}")
    public Response getFilteredRooms(@PathParam("filter") @Valid String filter) {
        Map<Integer, Room> rooms = rentableRepository.getFilteredRooms(filter);
        if(rooms != null) {
            return Response.ok(rooms).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/saunas")
    public Response getSaunas() {
        Map<Integer, Sauna> saunas = rentableRepository.getSaunas();
        if(saunas != null){
            return Response.ok(saunas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/saunas/{filter}")
    public Response getFilteredSaunas(@PathParam("filter") @Valid String filter) {
        Map<Integer, Sauna> saunas = rentableRepository.getFilteredSaunas(filter);
        if(saunas != null) {
            return Response.ok(saunas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rentable/{number}")
    public Response getRentable(@PathParam("number") @Valid int number) {
        Rentable r = rentableRepository.getRentable(number);
        if(r != null){
            return Response.ok(r).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rentable with number:"+ number +" doesn't exist").build();
    }
    
    @DELETE
    @Path("/rentable/{number}")
    public Response deleteRentable(@PathParam("number") @Valid int number) throws RentableRentedException
    {
        Rentable r = rentableRepository.getRentable(number);
        if(r != null) {
            if(checkIfIsNotRented(number)){
                rentableRepository.deleteRentable(rentableRepository.getRentable(number));
                return Response.ok("Room " + number + " deleted").build();
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Can't delete rented rentable").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rentable with number:"+ number +" doesn't exist").build();
    }
    
    public boolean checkIfIsNotRented(int number) {
        return rentRepository.getCurrentRents().values().stream().noneMatch((rent) -> (rent.getRentable().getNumber() == number));
    }
    
    public boolean checkIfExists(int number) {
        return rentableRepository.getRentables().containsKey(number);
    }
    
    @POST
    @Path("/room")
    public Response addRoom(@Valid Room room) {
        if(rentableRepository.getRentables().containsKey(room.getNumber())){
            return Response.status(Response.Status.FORBIDDEN).entity("Room or sauna with number:" + room.getNumber() + " already exists").build(); 
        }
        else rentableRepository.addRentable(room);
        return Response.ok("Room " + room.getNumber()  + " created").build();
    }
    
    @POST
    @Path("/sauna")
    public Response addSauna( @Valid Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(sauna.getNumber())){
             return Response.status(Response.Status.FORBIDDEN).entity("Room or sauna with number:" + sauna.getNumber() + " already exists").build(); 
        }
        else rentableRepository.addRentable(sauna);
        return Response.ok("Sauna " + sauna.getNumber()  + " created").build();
    }
    
    @PUT
    @Path("/room/{number}")
    public Response updateRoom(@PathParam("number") @Valid int number, @Valid Room room) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(number == room.getNumber()) {
                if(rentableRepository.getRentable(number) instanceof Room){
                  rentableRepository.updateRentable(number, room);
                  return Response.ok("Room " + room.getNumber()  + " updated").build();
                }
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Room number can't be changed").build(); 
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Room doesn't exist").build(); 
    }
       
    
    @PUT
    @Path("/sauna/{number}")
    public Response updateSauna(@PathParam("number") @Valid int number, @Valid Sauna sauna) {
        if(rentableRepository.getRentables().containsKey(number)) {
            if(number == sauna.getNumber()) {
                if(rentableRepository.getRentable(number) instanceof Sauna){
                    rentableRepository.updateRentable(number, sauna);
                    return Response.ok("Room " + sauna.getNumber()  + " updated").build();
                }
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Sauna number can't be changed").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Sauna doesn't exist").build(); 
    }
}

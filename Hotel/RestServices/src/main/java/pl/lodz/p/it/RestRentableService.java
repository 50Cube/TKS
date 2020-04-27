package pl.lodz.p.it;


//import com.mycompany.store.Services.CustomExceptions.RentableRentedException;
import pl.lodz.p.it.RestModel.RentableRest;
import pl.lodz.p.it.RestModel.RoomRest;
import pl.lodz.p.it.RestModel.SaunaRest;
import pl.lodz.p.it.RestPorts.Aggregates.RentableAdapterRestAndRA;
import pl.lodz.p.it.applicationPorts.Aggregates.RentAdapter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Named(value = "restRentableService")
@ApplicationScoped
@Path("model")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRentableService {
    
    @Inject
    private RentableAdapterRestAndRA rentableAdapter;
    
    @Inject
    private RentAdapter rentRepository;

    public RestRentableService() {
    }
    
    @GET
    @Path("/rentables")
    public Response getRentables() {
        Map<Integer, RentableRest> rentables = rentableAdapter.getRentables();
        if(rentables != null){
            return Response.ok(rentables).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rooms")
    public Response getRooms() {
        Map<Integer, RoomRest> rooms = rentableAdapter.getRooms();
        if(rooms != null){
            return Response.ok(rooms).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("rooms/{filter}")
    public Response getFilteredRooms(@PathParam("filter") @Valid String filter) {
        Map<Integer, RoomRest> rooms = rentableAdapter.getFilteredRooms(filter);
        if(rooms != null) {
            return Response.ok(rooms).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/saunas")
    public Response getSaunas() {
        Map<Integer, SaunaRest> saunas = rentableAdapter.getSaunas();
        if(saunas != null){
            return Response.ok(saunas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/saunas/{filter}")
    public Response getFilteredSaunas(@PathParam("filter") @Valid String filter) {
        Map<Integer, SaunaRest> saunas = rentableAdapter.getFilteredSaunas(filter);
        if(saunas != null) {
            return Response.ok(saunas).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("/rentable/{number}")
    public Response getRentable(@PathParam("number") @Valid int number) {
        RentableRest r = rentableAdapter.getRentable(number);
        if(r != null){
            return Response.ok(r).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Rentable with number:"+ number +" doesn't exist").build();
    }
    
    @DELETE
    @Path("/rentable/{number}")
    public Response deleteRentable(@PathParam("number") @Valid int number)// throws RentableRentedException
    {
        RentableRest r = rentableAdapter.getRentable(number);
        if(r != null) {
            if(checkIfIsNotRented(number)){
                rentableAdapter.deleteRentable(number);
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
        return rentableAdapter.getRentables().containsKey(number);
    }
    
    @POST
    @Path("/room")
    public Response addRoom(@Valid RoomRest room) {
        if(rentableAdapter.getRentables().containsKey(room.getNumber())){
            return Response.status(Response.Status.FORBIDDEN).entity("Room or sauna with number:" + room.getNumber() + " already exists").build(); 
        }
        else rentableAdapter.addRentable(room);
        return Response.ok("Room " + room.getNumber()  + " created").build();
    }
    
    @POST
    @Path("/sauna")
    public Response addSauna(@Valid SaunaRest sauna) {
        if(rentableAdapter.getRentables().containsKey(sauna.getNumber())){
             return Response.status(Response.Status.FORBIDDEN).entity("Room or sauna with number:" + sauna.getNumber() + " already exists").build(); 
        }
        else rentableAdapter.addRentable(sauna);
        return Response.ok("Sauna " + sauna.getNumber()  + " created").build();
    }
    
    @PUT
    @Path("/room/{number}")
    public Response updateRoom(@PathParam("number") @Valid int number, @Valid RoomRest room) {
        if(rentableAdapter.getRentables().containsKey(number)) {
            if(number == room.getNumber()) {
                if(rentableAdapter.getRentable(number) instanceof RoomRest){
                  rentableAdapter.updateRentable(number, room);
                  return Response.ok("Room " + room.getNumber()  + " updated").build();
                }
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Room number can't be changed").build(); 
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Room doesn't exist").build(); 
    }
       
    
    @PUT
    @Path("/sauna/{number}")
    public Response updateSauna(@PathParam("number") @Valid int number, @Valid SaunaRest sauna) {
        if(rentableAdapter.getRentables().containsKey(number)) {
            if(number == sauna.getNumber()) {
                if(rentableAdapter.getRentable(number) instanceof SaunaRest){
                    rentableAdapter.updateRentable(number, sauna);
                    return Response.ok("Room " + sauna.getNumber()  + " updated").build();
                }
            }
            else return Response.status(Response.Status.FORBIDDEN).entity("Sauna number can't be changed").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Sauna doesn't exist").build(); 
    }
}

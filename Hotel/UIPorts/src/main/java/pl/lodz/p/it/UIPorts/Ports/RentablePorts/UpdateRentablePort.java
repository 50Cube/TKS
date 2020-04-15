package pl.lodz.p.it.UIPorts.Ports.RentablePorts;

public interface UpdateRentablePort {
    void updateRoom(int number, double newArea, int newBeds);
    void updateSauna(int number, double newCost);
}

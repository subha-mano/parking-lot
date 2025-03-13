package ai.sahaj;

public class ParkingTicket {
    VehicleSpot vehicleSpot;

    public ParkingTicket(VehicleSpot vehicleSpot) {
        this.vehicleSpot = vehicleSpot;
    }

    public Vehicle vehicle() {
        return this.vehicleSpot.vehicle;
    }
}

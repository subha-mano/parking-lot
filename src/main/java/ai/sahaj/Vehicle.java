package ai.sahaj;

public class Vehicle {
    private VehicleType vehicleType;

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getParkingTicket() {
        return null;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }
}

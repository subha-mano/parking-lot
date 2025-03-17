package ai.sahaj;

public class VehicleSpot {
    private final String identifier;
    private final VehicleType vehicleType;
    Vehicle vehicle;

    public VehicleSpot(String identifier, VehicleType vehicleType) {
        this.identifier = identifier;
        this.vehicleType = vehicleType;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleType vehicleType() {
        return this.vehicleType;
    }

    public void unpark() {
        this.vehicle = null;
    }
}

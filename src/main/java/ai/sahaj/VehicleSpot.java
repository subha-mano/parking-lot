package ai.sahaj;

public class VehicleSpot {
    private final String identifier;
    Vehicle vehicle;

    public VehicleSpot(String identifier) {
        this.identifier = identifier;
    }

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

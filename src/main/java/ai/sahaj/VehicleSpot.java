package ai.sahaj;

public class VehicleSpot {
    Vehicle vehicle;

    public boolean isOccupied() {
        return this.vehicle != null;
    }

    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

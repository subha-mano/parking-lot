package ai.sahaj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingSpots {
    private final List<VehicleSpot> vehicleSpots;

    public ParkingSpots() {
        this.vehicleSpots = new ArrayList<>();
    }

    public void add(VehicleType vehicleType, int noOfSpots) {
        IntStream.range(0, noOfSpots)
                .forEach(i ->
                        this.vehicleSpots.add(new VehicleSpot("%d".formatted(i + 1), vehicleType)));
    }

    private VehicleSpot getFreeSpot(Vehicle vehicle) {
        return this.vehicleSpots.stream()
                .filter(vehicleSpot -> vehicle.getVehicleType().equals(vehicleSpot.vehicleType())
                        && !vehicleSpot.isOccupied())
                .findFirst()
                .orElse(null);
    }

    public VehicleSpot allot(Vehicle vehicle) {
        VehicleSpot vehicleSpot = this.getFreeSpot(vehicle);
        if(vehicleSpot != null)
            vehicleSpot.park(vehicle);
        return vehicleSpot;

    }
}

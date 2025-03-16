package ai.sahaj;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SpotAlloter {
    private final VehicleType vehicleType;
    private VehicleSpot[] vehicleSpots;

    public SpotAlloter(int noOfSpots, VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        initialiseSpots(noOfSpots);

    }

    private void initialiseSpots(int noOfBikeSpots) {
        this.vehicleSpots = new VehicleSpot[noOfBikeSpots];
        IntStream.range(0, noOfBikeSpots)
                .forEach(i -> this.vehicleSpots[i] = new VehicleSpot("%d".formatted(i + 1)));
    }

    private VehicleSpot getFreeSpot() {
        return Arrays.stream(this.vehicleSpots)
                .filter(vehicleSpot -> !vehicleSpot.isOccupied())
                .findFirst()
                .orElse(null);
    }

    public VehicleSpot allot(Vehicle vehicle) {
        if(!vehicle.getVehicleType().equals(this.vehicleType)) {
            return null;
        }
        VehicleSpot vehicleSpot = this.getFreeSpot();
        if(vehicleSpot != null)
            vehicleSpot.park(vehicle);
        return vehicleSpot;

    }
}

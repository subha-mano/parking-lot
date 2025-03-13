package ai.sahaj;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SpotAlloter {
    private VehicleSpot[] bikeSpots;

    public SpotAlloter(int noOfBikeSpots) {
        initialiseBikeSpots(noOfBikeSpots);
    }

    private void initialiseBikeSpots(int noOfBikeSpots) {
        this.bikeSpots = new VehicleSpot[noOfBikeSpots];
        IntStream.range(0, noOfBikeSpots)
                .forEach(i -> this.bikeSpots[i] = new VehicleSpot("%d".formatted(i + 1)));
    }

    public VehicleSpot getFreeSpot() {
        return Arrays.stream(this.bikeSpots)
                .filter(vehicleSpot -> !vehicleSpot.isOccupied())
                .findFirst()
                .orElse(null);
    }

    public VehicleSpot allot(Vehicle vehicle) {
        VehicleSpot vehicleSpot = this.getFreeSpot();
        if(vehicleSpot != null)
            vehicleSpot.park(vehicle);
        return vehicleSpot;

    }
}

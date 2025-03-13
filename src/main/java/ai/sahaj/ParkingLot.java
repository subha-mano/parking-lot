package ai.sahaj;

import java.util.Arrays;
import java.util.List;

public class ParkingLot {
    VehicleSpot[] bikeSpots;

    public ParkingLot(int noOfBikeSpots) {
        initialiseBikeSpots(noOfBikeSpots);
    }

    private void initialiseBikeSpots(int noOfBikeSpots) {
        this.bikeSpots = new VehicleSpot[noOfBikeSpots];
        for(int i = 0; i< noOfBikeSpots; i++){
            this.bikeSpots[i] = new VehicleSpot();
        }
    }

    public VehicleSpot assignSpot(Vehicle vehicle) {
       VehicleSpot vehicleSpot = this.getFreeSpot();
        if (vehicleSpot != null) {
            vehicleSpot.park(vehicle);
        }
        return vehicleSpot;
    }

    public List<VehicleSpot> getSpotsBooked() {
        return Arrays.stream(this.bikeSpots)
                .filter(VehicleSpot::isOccupied)
                .toList();
    }

    public VehicleSpot getFreeSpot() {
        for (VehicleSpot vehicleSpot : this.bikeSpots) {
            if (!vehicleSpot.isOccupied()) {
                return vehicleSpot;
            }
        }
        return null;
    }
}

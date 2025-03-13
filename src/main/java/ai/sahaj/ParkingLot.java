package ai.sahaj;

public class ParkingLot {
    final SpotAlloter spotAlloter;

    public ParkingLot(int noOfBikeSpots) {
        spotAlloter = new SpotAlloter(noOfBikeSpots);
    }

    public VehicleSpot allot(Vehicle vehicle) {
       return this.spotAlloter.allot(vehicle);
    }
}

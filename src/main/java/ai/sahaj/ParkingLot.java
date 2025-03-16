package ai.sahaj;

public class ParkingLot {
    final SpotAlloter spotAlloter;

    public ParkingLot(int noOfBikeSpots) {
        spotAlloter = new SpotAlloter(noOfBikeSpots, VehicleType.BIKE);
    }

    public ParkingTicket allot(Vehicle vehicle) {
        VehicleSpot vehicleSpot = this.spotAlloter.allot(vehicle);
        if(vehicleSpot == null) return null;

        return new ParkingTicket(vehicleSpot);
    }
}

package ai.sahaj;

public class ParkingLot {
    private final ParkingSpots parkingSpots;

    public ParkingLot(int noOfBikeSpots, int noOfCarSpots) {
        this.parkingSpots = new ParkingSpots();
        parkingSpots.add(VehicleType.BIKE, noOfBikeSpots);
        parkingSpots.add(VehicleType.CAR, noOfCarSpots);
    }

    public ParkingTicket allot(Vehicle vehicle) {
        VehicleSpot vehicleSpot = parkingSpots.allot(vehicle);
        if(vehicleSpot == null) return null;

        return new ParkingTicket(vehicleSpot);
    }
}

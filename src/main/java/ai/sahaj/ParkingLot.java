package ai.sahaj;

public class ParkingLot {
    private final ParkingSpots parkingSpots;

    public ParkingLot(int noOfBikeSpots, int noOfCarSpots) {
        this.parkingSpots = new ParkingSpots();
        parkingSpots.add(VehicleType.BIKE, noOfBikeSpots);
        parkingSpots.add(VehicleType.CAR, noOfCarSpots);
    }

    public ParkingTicket park(Vehicle vehicle) {
        VehicleSpot vehicleSpot = parkingSpots.getFreeSpot(vehicle);
        if(vehicleSpot == null) return null;
        vehicleSpot.park(vehicle);
        return new ParkingTicket(vehicleSpot);
    }
}

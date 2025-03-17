package ai.sahaj;

public class ParkingLot {
    private final Venue venue;

    public ParkingLot(int noOfBikeSpots, int noOfCarSpots) {
        this.venue = new Venue();
        venue.add(VehicleType.BIKE, noOfBikeSpots);
        venue.add(VehicleType.CAR, noOfCarSpots);
    }

    public ParkingTicket park(Vehicle vehicle) {
        VehicleSpot vehicleSpot = venue.getFreeSpot(vehicle);
        if(vehicleSpot == null) return null;
        vehicleSpot.park(vehicle);
        return new ParkingTicket(vehicleSpot);
    }

    public Receipt unpark(ParkingTicket parkingTicket) {
        parkingTicket.vehicleSpot().unpark();
        return new Receipt(parkingTicket);
    }
}

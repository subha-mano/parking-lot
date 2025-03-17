package ai.sahaj;

import ai.sahaj.feeModel.FeeModel;

public class ParkingLot {
    private final Venue venue;

    public ParkingLot(int noOfBikeSpots, int noOfCarSpots, int noOfBusSpots) {
        this.venue = new Venue(new FeeModel());
        venue.add(VehicleType.BIKE, noOfBikeSpots);
        venue.add(VehicleType.CAR, noOfCarSpots);
        venue.add(VehicleType.BUS, noOfBusSpots);
    }

    public ParkingTicket park(Vehicle vehicle) {
        VehicleSpot vehicleSpot = venue.getFreeSpot(vehicle);
        if(vehicleSpot == null) return null;
        vehicleSpot.park(vehicle);
        return new ParkingTicket(vehicleSpot);
    }

    public Receipt unpark(ParkingTicket parkingTicket) {
        parkingTicket.vehicleSpot().unpark();
        return new Receipt(parkingTicket, this.venue.feeModel());
    }
}

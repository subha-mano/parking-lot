package ai.sahaj;

import ai.sahaj.fee_model.FeeModel;

public class ParkingLot {
    private final Venue venue;

    public ParkingLot(int noOfBikeSpots, int noOfCarSpots, int noOfBusSpots, FeeModel feeModel) {
        this.venue = new Venue(feeModel);
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

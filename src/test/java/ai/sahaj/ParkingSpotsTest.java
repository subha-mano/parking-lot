package ai.sahaj;

import ai.sahaj.feeModel.FeeModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotsTest {
    private static Vehicle getBike() {
        return new Vehicle(VehicleType.BIKE);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        Venue venue = new Venue(new FeeModel());
        Vehicle vehicle = getBike();

        assertNull(venue.getFreeSpot(vehicle));
    }

    @Test
    void shouldGetSpotForAVehicleToPark() {
        Venue venue = new Venue(new FeeModel());
        venue.add(VehicleType.BIKE, 2);
        Vehicle vehicle = getBike();

        VehicleSpot vehicleSpot = venue.getFreeSpot(vehicle);

        assertNotNull(vehicleSpot);
    }

    @Test
    void shouldNotGetSpotIfFull() {
        Venue venue = new Venue(new FeeModel());
        venue.add(VehicleType.BIKE, 1);
        Vehicle vehicle = getBike();
        Vehicle anotherVehicle = getBike();

        VehicleSpot vehicleSpot1 = venue.getFreeSpot(vehicle);
        vehicleSpot1.park(vehicle);
        VehicleSpot vehicleSpot2 = venue.getFreeSpot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfSpotIsNotOfTheSameType() {
        Venue venue = new Venue(new FeeModel());
        venue.add(VehicleType.CAR, 1);
        Vehicle vehicle = getBike();

        VehicleSpot vehicleSpot = venue.getFreeSpot(vehicle);

        assertNull(vehicleSpot);
    }
}
package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotsTest {
    private static Vehicle getBike() {
        return new Vehicle(VehicleType.BIKE);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingSpots parkingSpots = new ParkingSpots();
        Vehicle vehicle = getBike();

        assertNull(parkingSpots.getFreeSpot(vehicle));
    }

    @Test
    void shouldGetSpotForAVehicleToPark() {
        ParkingSpots parkingSpots = new ParkingSpots();
        parkingSpots.add(VehicleType.BIKE, 2);
        Vehicle vehicle = getBike();

        VehicleSpot vehicleSpot = parkingSpots.getFreeSpot(vehicle);

        assertNotNull(vehicleSpot);
    }

    @Test
    void shouldNotGetSpotIfFull() {
        ParkingSpots parkingSpots = new ParkingSpots();
        parkingSpots.add(VehicleType.BIKE, 1);
        Vehicle vehicle = getBike();
        Vehicle anotherVehicle = getBike();

        VehicleSpot vehicleSpot1 = parkingSpots.getFreeSpot(vehicle);
        vehicleSpot1.park(vehicle);
        VehicleSpot vehicleSpot2 = parkingSpots.getFreeSpot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfSpotIsNotOfTheSameType() {
        ParkingSpots parkingSpots = new ParkingSpots();
        parkingSpots.add(VehicleType.CAR, 1);
        Vehicle vehicle = getBike();

        VehicleSpot vehicleSpot = parkingSpots.getFreeSpot(vehicle);

        assertNull(vehicleSpot);
    }
}
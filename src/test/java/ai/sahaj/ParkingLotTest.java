package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingLot parkingLot = new ParkingLot(0);
        Vehicle vehicle = new Vehicle();

        assertNull(parkingLot.allot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();

        VehicleSpot vehicleSpot = parkingLot.allot(vehicle);

        assertEquals(vehicleSpot.vehicle, vehicle);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        Vehicle anotherVehicle = new Vehicle();

        VehicleSpot vehicleSpot1 = parkingLot.allot(vehicle);
        VehicleSpot vehicleSpot2 = parkingLot.allot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }

}
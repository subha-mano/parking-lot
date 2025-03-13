package ai.sahaj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingLot parkingLot = new ParkingLot(0);
        Vehicle vehicle = new Vehicle();

        assertNull(parkingLot.assignSpot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();

        VehicleSpot vehicleSpot = parkingLot.assignSpot(vehicle);

        assertEquals(vehicleSpot.vehicle, vehicle);
    }

    @Test
    void shouldGetBookedSpotsAfterParking() {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();

        parkingLot.assignSpot(vehicle);
        List<VehicleSpot> spotsBooked = parkingLot.getSpotsBooked();

        assertEquals(1, spotsBooked.size());
        assertEquals(vehicle, spotsBooked.getFirst().vehicle);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        Vehicle anotherVehicle = new Vehicle();

        VehicleSpot vehicleSpot1 = parkingLot.assignSpot(vehicle);
        VehicleSpot vehicleSpot2 = parkingLot.assignSpot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }
}
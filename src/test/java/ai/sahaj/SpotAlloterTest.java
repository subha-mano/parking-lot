package ai.sahaj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotAlloterTest {
    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        SpotAlloter spotAlloter = new SpotAlloter(0);
        Vehicle vehicle = new Vehicle();

        assertNull(spotAlloter.allot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        SpotAlloter spotAlloter = new SpotAlloter(2);
        Vehicle vehicle = new Vehicle();

        VehicleSpot vehicleSpot = spotAlloter.allot(vehicle);

        assertEquals(vehicleSpot.vehicle, vehicle);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        SpotAlloter spotAlloter = new SpotAlloter(1);
        Vehicle vehicle = new Vehicle();
        Vehicle anotherVehicle = new Vehicle();

        VehicleSpot vehicleSpot1 = spotAlloter.allot(vehicle);
        VehicleSpot vehicleSpot2 = spotAlloter.allot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }
}
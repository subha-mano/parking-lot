package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpotAlloterTest {
    private static Vehicle getVehicle() {
        return new Vehicle(VehicleType.BIKE);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        SpotAlloter spotAlloter = new SpotAlloter(0, VehicleType.BIKE);
        Vehicle vehicle = getVehicle();

        assertNull(spotAlloter.allot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        SpotAlloter spotAlloter = new SpotAlloter(2, VehicleType.BIKE);
        Vehicle vehicle = getVehicle();

        VehicleSpot vehicleSpot = spotAlloter.allot(vehicle);

        assertEquals(vehicleSpot.vehicle, vehicle);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        SpotAlloter spotAlloter = new SpotAlloter(1, VehicleType.BIKE);
        Vehicle vehicle = getVehicle();
        Vehicle anotherVehicle = getVehicle();

        VehicleSpot vehicleSpot1 = spotAlloter.allot(vehicle);
        VehicleSpot vehicleSpot2 = spotAlloter.allot(anotherVehicle);

        assertNotNull(vehicleSpot1.vehicle);
        assertNull(vehicleSpot2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfSpotIsNotOfTheSameType() {
        SpotAlloter spotAlloter = new SpotAlloter(1 , VehicleType.CAR);
        Vehicle vehicle = getVehicle();

        VehicleSpot vehicleSpot = spotAlloter.allot(vehicle);

        assertNull(vehicleSpot);
    }
}
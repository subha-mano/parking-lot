package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleSpotTest {
    @Test
    void shouldReturnTrueIfOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot("1", VehicleType.CAR);

        assertFalse(vehicleSpot.isOccupied());
    }

    @Test
    void shouldReturnFalseIfNotOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot("1", VehicleType.BIKE);

        vehicleSpot.park(getBike());

        assertTrue(vehicleSpot.isOccupied());
    }

    @Test
    void shouldParkTheVehicle() {
        VehicleSpot vehicleSpot = new VehicleSpot("1", VehicleType.CAR);

        Vehicle bike = getBike();
        vehicleSpot.park(bike);

        assertEquals(vehicleSpot.vehicle, bike);
    }

    @Test
    void shouldUnParkTheVehicle() {
        VehicleSpot vehicleSpot = new VehicleSpot("1", VehicleType.CAR);

        Vehicle bike = getBike();
        vehicleSpot.park(bike);
        vehicleSpot.unpark();

        assertNull(vehicleSpot.vehicle);
    }

    private static Vehicle getBike() {
        return new Vehicle(VehicleType.BIKE);
    }

}
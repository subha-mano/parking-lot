package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleSpotTest {
    @Test
    void shouldReturnTrueIfOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot("1");

        assertFalse(vehicleSpot.isOccupied());
    }

    @Test
    void shouldReturnFalseIfNotOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot("1");

        vehicleSpot.park(new Vehicle(VehicleType.BIKE));

        assertTrue(vehicleSpot.isOccupied());
    }
}
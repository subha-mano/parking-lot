package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleSpotTest {
    @Test
    void shouldReturnTrueIfOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot();

        assertFalse(vehicleSpot.isOccupied());
    }

    @Test
    void shouldReturnFalseIfNotOccupied() {
        VehicleSpot vehicleSpot = new VehicleSpot();

        vehicleSpot.park(new Vehicle());

        assertTrue(vehicleSpot.isOccupied());
    }
}
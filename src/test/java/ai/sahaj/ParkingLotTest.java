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

        ParkingTicket parkingTicket= parkingLot.allot(vehicle);

        assertEquals(parkingTicket.vehicle(), vehicle);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        Vehicle anotherVehicle = new Vehicle();

        ParkingTicket parkingTicket1 = parkingLot.allot(vehicle);
        ParkingTicket parkingTicket2 = parkingLot.allot(anotherVehicle);

        assertNotNull(parkingTicket1.vehicle());
        assertNull(parkingTicket2);
    }

}
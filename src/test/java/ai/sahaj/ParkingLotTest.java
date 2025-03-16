package ai.sahaj;

import org.junit.jupiter.api.Test;

import static ai.sahaj.VehicleType.BIKE;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    private static Vehicle getVehicle() {
        return new Vehicle(BIKE);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingLot parkingLot = new ParkingLot(0);
        Vehicle vehicle = getVehicle();

        assertNull(parkingLot.allot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = getVehicle();

        ParkingTicket parkingTicket= parkingLot.allot(vehicle);

        System.out.println(parkingTicket.startTime());

        assertEquals(vehicle, parkingTicket.vehicle());
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = getVehicle();
        Vehicle anotherVehicle = getVehicle();

        ParkingTicket parkingTicket1 = parkingLot.allot(vehicle);
        ParkingTicket parkingTicket2 = parkingLot.allot(anotherVehicle);

        assertNotNull(parkingTicket1.vehicle());
        assertNull(parkingTicket2);
    }
}
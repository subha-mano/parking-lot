package ai.sahaj;

import org.junit.jupiter.api.Test;

import static ai.sahaj.VehicleType.BIKE;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    private static Vehicle getCar() {
        return new Vehicle(VehicleType.CAR);
    }

    private static Vehicle getBike() {
        return new Vehicle(BIKE);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingLot parkingLot = new ParkingLot(0, 0);
        Vehicle vehicle = getBike();

        assertNull(parkingLot.allot(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.allot(vehicle);

        assertEquals(vehicle, parkingTicket.vehicle());
    }

    @Test
    void shouldLinkSpotOfSameTypeToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.allot(vehicle);

        assertEquals(vehicle.getVehicleType(), parkingTicket.vehicleType());
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1, 0);
        Vehicle vehicle = getBike();
        Vehicle anotherVehicle = getBike();

        ParkingTicket parkingTicket1 = parkingLot.allot(vehicle);
        ParkingTicket parkingTicket2 = parkingLot.allot(anotherVehicle);

        assertNotNull(parkingTicket1.vehicle());
        assertNull(parkingTicket2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfOfDifferentType() {
        ParkingLot parkingLot = new ParkingLot(1, 0);
        Vehicle vehicle = getCar();

        ParkingTicket parkingTicket = parkingLot.allot(vehicle);

        assertNull(parkingTicket);
    }

    @Test
    void shoulAssignSpotsForDifferentTypes() {
        ParkingLot parkingLot = new ParkingLot(1, 2);
        Vehicle car = getCar();
        Vehicle bike = getBike();

        ParkingTicket parkingTicketForCar = parkingLot.allot(car);
        ParkingTicket parkingTicketForBike = parkingLot.allot(bike);

        assertNotNull(parkingTicketForCar);
        assertNotNull(parkingTicketForBike);
    }
}
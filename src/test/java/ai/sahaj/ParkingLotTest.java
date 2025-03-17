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

        assertNull(parkingLot.park(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.park(vehicle);

        assertEquals(vehicle, parkingTicket.vehicle());
    }

    @Test
    void shouldLinkSpotOfSameTypeToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.park(vehicle);

        assertEquals(vehicle.getVehicleType(), parkingTicket.vehicleType());
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1, 0);
        Vehicle vehicle = getBike();
        Vehicle anotherVehicle = getBike();

        ParkingTicket parkingTicket1 = parkingLot.park(vehicle);
        ParkingTicket parkingTicket2 = parkingLot.park(anotherVehicle);

        assertNotNull(parkingTicket1.vehicle());
        assertNull(parkingTicket2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfOfDifferentType() {
        ParkingLot parkingLot = new ParkingLot(1, 0);
        Vehicle vehicle = getCar();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);

        assertNull(parkingTicket);
    }

    @Test
    void shoulAssignSpotsForDifferentTypes() {
        ParkingLot parkingLot = new ParkingLot(1, 2);
        Vehicle car = getCar();
        Vehicle bike = getBike();

        ParkingTicket parkingTicketForCar = parkingLot.park(car);
        ParkingTicket parkingTicketForBike = parkingLot.park(bike);

        assertNotNull(parkingTicketForCar);
        assertNotNull(parkingTicketForBike);
    }
}
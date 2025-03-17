package ai.sahaj;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.Instant;

import static ai.sahaj.VehicleType.BIKE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ParkingLotTest {
    MockedStatic<Instant> mockedInstant;

    @BeforeEach
    void setup() {
        mockedInstant = mockStatic(Instant.class);
    }

    @AfterEach
    void tearDown() {
        mockedInstant.close();
    }

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
    void shouldCaptureStartTimeWhenAVehicleIsParked() {
        String mockTime = "2020-01-01T10:10:10Z";
        Instant nowInstant = Instant.parse(mockTime);
        mockedInstant.when(Instant::now).thenReturn(nowInstant);

        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);

        assertEquals(nowInstant, parkingTicket.startTime());
    }

    @Test
    void shouldGetReceiptWhenAVehicleIsUnParked() {
        Instant startInstant = Instant.parse("2020-01-01T10:10:10Z");
        Instant endInstant = Instant.parse("2020-01-01T12:10:10Z");
        mockedInstant.when(Instant::now).thenReturn(startInstant, endInstant);

        ParkingLot parkingLot = new ParkingLot(2, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);
        Receipt receipt = parkingLot.unpark(parkingTicket);

        assertEquals(0.0, receipt.fees);
        assertEquals(startInstant, receipt.entryTime);
        assertEquals(endInstant, receipt.exitTime);
    }

    @Test
    void shouldAllowAnotherVehicleToParkWhenAVehicleIsUnParked() {
        ParkingLot parkingLot = new ParkingLot(1, 0);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket1 = parkingLot.park(vehicle);
        parkingLot.unpark(parkingTicket1);
        ParkingTicket parkingTicket2 = parkingLot.park(vehicle);

        assertNotNull(parkingTicket2);
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
    void shouldAssignSpotsForDifferentTypes() {
        ParkingLot parkingLot = new ParkingLot(1, 2);
        Vehicle car = getCar();
        Vehicle bike = getBike();

        ParkingTicket parkingTicketForCar = parkingLot.park(car);
        ParkingTicket parkingTicketForBike = parkingLot.park(bike);

        assertNotNull(parkingTicketForCar);
        assertNotNull(parkingTicketForBike);
    }
}
package ai.sahaj;

import ai.sahaj.fee_model.FeeModel;
import ai.sahaj.fee_model.FlatHourFeeModel;
import ai.sahaj.fee_model.StadiumFeeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import java.time.Instant;

import static ai.sahaj.VehicleType.BIKE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotTest {
    MockedStatic<Instant> mockedInstant;
    FeeModel flatHourFeeModel = new FlatHourFeeModel();

    @BeforeEach
    void setup() {
        mockedInstant = mockStatic(Instant.class, CALLS_REAL_METHODS);
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

    private static Vehicle getBus() {
        return new Vehicle(VehicleType.BUS);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfNoParkingSpots() {
        ParkingLot parkingLot = new ParkingLot(0, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        assertNull(parkingLot.park(vehicle));
    }

    @Test
    void shouldLinkSpotToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.park(vehicle);

        assertEquals(vehicle, parkingTicket.vehicle());
    }

    @Test
    void shouldCaptureStartTimeWhenAVehicleIsParked() {
        String mockTime = "2020-01-01T10:10:10Z";
        Instant nowInstant = Instant.parse(mockTime);
        mockedInstant.when(Instant::now).thenReturn(nowInstant);

        ParkingLot parkingLot = new ParkingLot(2, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);

        assertEquals(nowInstant, parkingTicket.startTime());
    }

    @Test
    void shouldLinkSpotOfBikeTypeToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket= parkingLot.park(vehicle);

        assertEquals(vehicle.getVehicleType(), parkingTicket.vehicleType());
    }

    @Test
    void shouldLinkSpotOfBusTypeToAVehicleToPark() {
        ParkingLot parkingLot = new ParkingLot(2, 0, 2, flatHourFeeModel);
        Vehicle vehicle = getBus();

        ParkingTicket parkingTicket= parkingLot.park(vehicle);

        assertEquals(vehicle.getVehicleType(), parkingTicket.vehicleType());
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfFull() {
        ParkingLot parkingLot = new ParkingLot(1, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();
        Vehicle anotherVehicle = getBike();

        ParkingTicket parkingTicket1 = parkingLot.park(vehicle);
        ParkingTicket parkingTicket2 = parkingLot.park(anotherVehicle);

        assertNotNull(parkingTicket1.vehicle());
        assertNull(parkingTicket2);
    }

    @Test
    void shouldNotAssignSpotToAVehicleIfOfDifferentType() {
        ParkingLot parkingLot = new ParkingLot(1, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getCar();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);

        assertNull(parkingTicket);
    }

    @Test
    void shouldNotAssignSpotToABusIfOfDifferentType() {
        ParkingLot parkingLot = new ParkingLot(1, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBus();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);

        assertNull(parkingTicket);
    }

    @Test
    void shouldAssignSpotsForDifferentTypes() {
        ParkingLot parkingLot = new ParkingLot(1, 2, 0, flatHourFeeModel);
        Vehicle car = getCar();
        Vehicle bike = getBike();

        ParkingTicket parkingTicketForCar = parkingLot.park(car);
        ParkingTicket parkingTicketForBike = parkingLot.park(bike);

        assertNotNull(parkingTicketForCar);
        assertNotNull(parkingTicketForBike);
    }

    @Test
    void shouldGetReceiptWhenAVehicleIsUnParked() {
        Instant startInstant = Instant.parse("2020-01-01T10:10:10Z");
        Instant endInstant = Instant.parse("2020-01-01T12:10:10Z");
        mockedInstant.when(Instant::now).thenReturn(startInstant, endInstant);

        ParkingLot parkingLot = new ParkingLot(2, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket = parkingLot.park(vehicle);
        Receipt receipt = parkingLot.unpark(parkingTicket);

        assertEquals(startInstant, receipt.entryTime);
        assertEquals(endInstant, receipt.exitTime);
    }
    @Test
    void shouldAllowAnotherVehicleToParkWhenAVehicleIsUnParked() {
        ParkingLot parkingLot = new ParkingLot(1, 0, 0, flatHourFeeModel);
        Vehicle vehicle = getBike();

        ParkingTicket parkingTicket1 = parkingLot.park(vehicle);
        parkingLot.unpark(parkingTicket1);
        ParkingTicket parkingTicket2 = parkingLot.park(vehicle);

        assertNotNull(parkingTicket2);
    }

    @Nested
    class MallFlatHourFeeModelTest {
        @ParameterizedTest(name = "[{index}] {3}")
        @CsvSource({
                "2020-01-01T10:10:10Z,2020-01-01T12:10:10Z,20,exact_hours",
                "2020-01-01T08:10:10Z,2020-01-01T12:10:10Z,40,one_minute_more",
                "2020-01-01T06:10:10Z,2020-01-01T11:09:10Z,50,one_minute_less",
                "2020-01-01T06:10:10Z,2020-01-02T17:09:10Z,350,more_than_a_day"
        })
        void shouldCalculatePerHourFlatFeesForBike(String startTime, String endTime, double expectedFees, String desc) {
            Instant start = Instant.parse(startTime);
            Instant end = Instant.parse(endTime);
            when(Instant.now()).thenReturn(start, end);

            ParkingLot parkingLot = new ParkingLot(2, 0, 0, flatHourFeeModel);
            Vehicle vehicle = getBike();

            ParkingTicket parkingTicket = parkingLot.park(vehicle);
            Receipt receipt = parkingLot.unpark(parkingTicket);

            assertEquals(expectedFees, receipt.fees);
        }

        @ParameterizedTest(name = "[{index}] {3}")
        @CsvSource({
                "2020-01-01T10:10:10Z,2020-01-01T12:10:10Z,40,exact_hours",
                "2020-01-01T08:10:10Z,2020-01-01T12:10:10Z,80,one_minute_more",
                "2020-01-01T06:10:10Z,2020-01-01T11:09:10Z,100,one_minute_less",
                "2020-01-01T06:10:10Z,2020-01-02T17:09:10Z,700,more_than_a_day"
        })
        void shouldCalculatePerHourFlatFeesForCar(String startTime, String endTime, double expectedFees, String desc) {
            Instant start = Instant.parse(startTime);
            Instant end = Instant.parse(endTime);
            when(Instant.now()).thenReturn(start, end);

            ParkingLot parkingLot = new ParkingLot(0, 2, 0, flatHourFeeModel);
            Vehicle vehicle = getCar();

            ParkingTicket parkingTicket = parkingLot.park(vehicle);
            Receipt receipt = parkingLot.unpark(parkingTicket);

            assertEquals(expectedFees, receipt.fees);
        }

        @ParameterizedTest(name = "[{index}] {3}")
        @CsvSource({
                "2020-01-01T10:10:10Z,2020-01-01T12:10:10Z,100,exact_hours",
                "2020-01-01T08:10:10Z,2020-01-01T12:10:10Z,200,one_minute_more",
                "2020-01-01T06:10:10Z,2020-01-01T11:09:10Z,250,one_minute_less",
                "2020-01-01T06:10:10Z,2020-01-02T17:09:10Z,1750,more_than_a_day"
        })
        void shouldCalculatePerHourFlatFeesForBus(String startTime, String endTime, double expectedFees, String desc) {
            Instant start = Instant.parse(startTime);
            Instant end = Instant.parse(endTime);
            when(Instant.now()).thenReturn(start, end);

            ParkingLot parkingLot = new ParkingLot(2, 2, 4, flatHourFeeModel);
            Vehicle vehicle = getBus();

            ParkingTicket parkingTicket = parkingLot.park(vehicle);
            Receipt receipt = parkingLot.unpark(parkingTicket);

            assertEquals(expectedFees, receipt.fees);
        }
    }

    @Nested
    class StadiumFeeModelTest {
        @ParameterizedTest(name = "[{index}] {3}")
        @CsvSource({
                "2020-01-01T10:10:10Z,2020-01-01T10:11:10Z,30,one_minute",
                "2020-01-01T10:10:10Z,2020-01-01T12:10:10Z,30,exact_hours_two",
                "2020-01-01T10:10:10Z,2020-01-01T13:20:10Z,30,three_hours_ten_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T14:09:10Z,30,three_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T14:10:10Z,90,exact_four_hours",
                "2020-01-01T10:10:10Z,2020-01-01T14:11:10Z,90,four_hours_one_minute",
                "2020-01-01T10:10:10Z,2020-01-01T17:10:10Z,90,seven_hours",
                "2020-01-01T10:10:10Z,2020-01-01T22:09:10Z,90,eleven_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T22:10:10Z,90,twelve_hours",
                "2020-01-01T10:10:10Z,2020-01-01T22:11:10Z,190,twelve_hours_one_minute",
                "2020-01-01T10:10:10Z,2020-01-02T01:10:10Z,390,fourteen_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-02T01:10:10Z,390,fifteen_hours",
        })
        void shouldCalculateFeesForBike(String startTime, String endTime, double expectedFees, String desc) {
            Instant start = Instant.parse(startTime);
            Instant end = Instant.parse(endTime);
            when(Instant.now()).thenReturn(start, end);

            StadiumFeeModel feeModel = new StadiumFeeModel();
            ParkingLot parkingLot = new ParkingLot(2, 0, 0, feeModel);
            Vehicle vehicle = getBike();

            ParkingTicket parkingTicket = parkingLot.park(vehicle);
            Receipt receipt = parkingLot.unpark(parkingTicket);

            assertEquals(expectedFees, receipt.fees);
        }

        @ParameterizedTest(name = "[{index}] {3}")
        @CsvSource({
                "2020-01-01T10:10:10Z,2020-01-01T10:11:10Z,60,one_minute",
                "2020-01-01T10:10:10Z,2020-01-01T12:10:10Z,60,exact_hours_two",
                "2020-01-01T10:10:10Z,2020-01-01T13:20:10Z,60,three_hours_ten_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T14:09:10Z,60,three_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T14:10:10Z,180,exact_four_hours",
                "2020-01-01T10:10:10Z,2020-01-01T14:11:10Z,180,four_hours_one_minute",
                "2020-01-01T10:10:10Z,2020-01-01T17:10:10Z,180,seven_hours",
                "2020-01-01T10:10:10Z,2020-01-01T22:09:10Z,180,eleven_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-01T22:10:10Z,180,twelve_hours",
                "2020-01-01T10:10:10Z,2020-01-01T22:11:10Z,380,twelve_hours_one_minute",
                "2020-01-01T10:10:10Z,2020-01-02T01:10:10Z,780,fourteen_hours_fifty_nine_minutes",
                "2020-01-01T10:10:10Z,2020-01-02T01:10:10Z,780,fifteen_hours",
        })
        void shouldCalculateFeesForCar(String startTime, String endTime, double expectedFees, String desc) {
            Instant start = Instant.parse(startTime);
            Instant end = Instant.parse(endTime);
            when(Instant.now()).thenReturn(start, end);

            StadiumFeeModel feeModel = new StadiumFeeModel();
            ParkingLot parkingLot = new ParkingLot(2, 2, 0, feeModel);
            Vehicle vehicle = getCar();

            ParkingTicket parkingTicket = parkingLot.park(vehicle);
            Receipt receipt = parkingLot.unpark(parkingTicket);

            assertEquals(expectedFees, receipt.fees);
        }

    }
}
package ai.sahaj;

import java.time.Instant;

public class ParkingTicket {
    VehicleSpot vehicleSpot;
    private final Instant startTime;

    public ParkingTicket(VehicleSpot vehicleSpot) {
        this.vehicleSpot = vehicleSpot;
        this.startTime = Instant.now();
    }

    public Vehicle vehicle() {
        return this.vehicleSpot.vehicle;
    }

    public Instant startTime() {
        return this.startTime;
    }
}

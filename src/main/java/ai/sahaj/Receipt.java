package ai.sahaj;

import java.time.Instant;

public class Receipt {
    final double fees;
    final Instant entryTime;
    final Instant exitTime;

    public Receipt(ParkingTicket parkingTicket) {
        this.entryTime = parkingTicket.startTime();
        this.exitTime = Instant.now();
        this.fees = 0;
    }
}

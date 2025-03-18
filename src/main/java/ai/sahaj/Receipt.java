package ai.sahaj;

import ai.sahaj.feeModel.FeeModel;

import java.time.Duration;
import java.time.Instant;

public class Receipt {
    final double fees;
    final Instant entryTime;
    final Instant exitTime;

    public Receipt(ParkingTicket parkingTicket, FeeModel feeModel) {
        this.entryTime = parkingTicket.startTime();
        this.exitTime = Instant.now();
        long minutes = Duration.between(this.entryTime, this.exitTime).toMinutes();
        int hours = (int) Math.ceil(minutes / 60.0);
        this.fees = feeModel.fees(parkingTicket.vehicleType(), hours);
    }
}

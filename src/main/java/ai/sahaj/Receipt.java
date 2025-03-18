package ai.sahaj;

import ai.sahaj.fee_model.FeeModel;

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
        this.fees = feeModel.fees(parkingTicket.vehicleType(), minutes);
    }
}

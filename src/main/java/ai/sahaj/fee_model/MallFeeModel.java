package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.rate.PerHourRate;

import static ai.sahaj.VehicleType.*;

public class MallFeeModel implements FeeModel {
    public int fees(VehicleType vehicleType, long minutes) {
        int hours = (int) Math.ceil(minutes / 60.0);
        if (vehicleType == BIKE) return new PerHourRate(10).fees(hours);
        else if(vehicleType == CAR) return new PerHourRate(20).fees(hours);
        else if(vehicleType == BUS ) return new PerHourRate(50).fees(hours);
        return 0;
    }
}

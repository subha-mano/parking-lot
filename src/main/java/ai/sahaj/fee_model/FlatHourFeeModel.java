package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

public class FlatHourFeeModel implements FeeModel {
    public int fees(VehicleType vehicleType, long minutes) {
        int hours = (int) Math.ceil(minutes / 60.0);
        return hours * vehicleType.rate;
    }
}

package ai.sahaj.feeModel;

import ai.sahaj.VehicleType;

public class FeeModel {
    public double fees(VehicleType vehicleType, long hours) {
        return hours * vehicleType.rate;
    }
}

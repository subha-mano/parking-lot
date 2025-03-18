package ai.sahaj.feeModel;

import ai.sahaj.VehicleType;

public class FeeModel {
    public int fees(VehicleType vehicleType, int hours) {
        return hours * vehicleType.rate;
    }
}

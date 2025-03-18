package ai.sahaj.feeModel;

import ai.sahaj.VehicleType;

public interface FeeModel {
    int fees(VehicleType vehicleType, int hours);
}


package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

public interface FeeModel {
    int fees(VehicleType vehicleType, long minutes);
}


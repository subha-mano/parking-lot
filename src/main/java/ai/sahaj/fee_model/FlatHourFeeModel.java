package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

import static ai.sahaj.VehicleType.*;

public class FlatHourFeeModel implements FeeModel {
    public int fees(VehicleType vehicleType, long minutes) {
        int hours = (int) Math.ceil(minutes / 60.0);
        int rate = 0;
        if (vehicleType == BIKE) rate = 10;
        else if(vehicleType == CAR )rate = 20;
        else if(vehicleType == BUS )rate = 50;
        return hours * rate;
    }
}

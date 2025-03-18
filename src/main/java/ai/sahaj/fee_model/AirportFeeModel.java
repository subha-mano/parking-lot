package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

public class AirportFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        if (vehicleType == VehicleType.BIKE) {
            if (hours < 1)
                return 0;
            else if (hours >= 1 && hours < 8)
                return 40;
            else if (hours >= 8 && hours < 24)
                return 60;
            else
                return 80 * (int) Math.ceil(hours / 24);
        } else if (vehicleType == VehicleType.CAR) {
            if (hours < 12)
                return 60;
            else if (hours >= 12 && hours < 24)
                return 80;
            else if (hours >= 24)
                return 100 * (int) Math.ceil(hours / 24);
        }
        return 0;
    }
}

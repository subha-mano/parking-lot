package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

public class StadiumFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        int hours = (int) Math.ceil(minutes / 60.0);
        int fees = 0;
        if(vehicleType.equals(VehicleType.BIKE)){
            if(hours <= 4)
                fees += 30;
            else if(hours <= 12)
                fees += 30 + 60;
            else
                fees = 30 + 60 + (100 * (hours -12));
        } else if(vehicleType.equals(VehicleType.CAR)){
            if(hours <= 4)
                fees += 60;
            else if(hours <= 12)
                fees += 60 + 120;
            else
                fees = 60 + 120 + (200 * (hours -12));
        }
        return fees;
    }
}

package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.utils.Interval;

public class StadiumFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        int fees = 0;
        Interval intervalFromZeroToFour = new Interval(0, 4);
        Interval intervalFromFourToTwelve = new Interval(4, 12);

        if(vehicleType.equals(VehicleType.BIKE)){
            if(intervalFromZeroToFour.includes(hours))
                fees += 30;
            else {
                if(intervalFromFourToTwelve.includes(hours))
                    fees += 30 + 60;
                else
                    fees = 30 + 60 + (100 * ((int) Math.ceil(hours) -12));
            }
        } else if(vehicleType.equals(VehicleType.CAR)){
            if(intervalFromZeroToFour.includes(hours))
                fees += 60;
            else if(intervalFromFourToTwelve.includes(hours))
                fees += 60 + 120;
            else
                fees = 60 + 120 + (200 * ((int)Math.ceil(hours) - 12));
        }
        return fees;
    }
}

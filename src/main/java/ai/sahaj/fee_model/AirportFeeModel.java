package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.utils.Interval;

public class AirportFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        if (vehicleType == VehicleType.BIKE) {
            Interval intervalFromZeroToOne = new Interval(0, 1);
            Interval intervalFromOneToEight = new Interval(1, 8);
            Interval intervalFromEightToTwentyFour = new Interval(8, 24);
            if (intervalFromZeroToOne.includes(hours))
                return 0;
            else if (intervalFromOneToEight.includes(hours))
                return 40;
            else if (intervalFromEightToTwentyFour.includes(hours))
                return 60;
            else
                return 80 * (int) Math.ceil(hours / 24);
        } else if (vehicleType == VehicleType.CAR) {
            Interval intervalFromZeroToTwelve = new Interval(0, 12);
            Interval intervalFromTwelveToTwentyFour = new Interval(12, 24);
            if (intervalFromZeroToTwelve.includes(hours))
                return 60;
            else if (intervalFromTwelveToTwentyFour.includes(hours))
                return 80;
            else
                return 100 * (int) Math.ceil(hours / 24);
        }
        return 0;
    }
}

package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.fee_aggregators.CumulativeFeeAggregator;
import ai.sahaj.fee_model.fee_aggregators.FeeAggregator;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.fee_model.rate.PerHourRate;
import ai.sahaj.utils.Interval;

import static ai.sahaj.VehicleType.BIKE;
import static ai.sahaj.VehicleType.CAR;

public class StadiumFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        Rule[] rules = new Rule[]{
                new Rule(new Interval(0, 4), new FlatRate(30), BIKE),
                new Rule(new Interval(4, 12), new FlatRate(60), BIKE),
                new Rule(new Interval(12, Integer.MAX_VALUE), new PerHourRate(100), BIKE),
                new Rule(new Interval(0, 4), new FlatRate(60), CAR),
                new Rule(new Interval(4, 12), new FlatRate(120), CAR),
                new Rule(new Interval(12, Integer.MAX_VALUE), new PerHourRate(200), CAR),
        };

        return new CumulativeFeeAggregator().aggregate(rules, hours, vehicleType);
    }

}

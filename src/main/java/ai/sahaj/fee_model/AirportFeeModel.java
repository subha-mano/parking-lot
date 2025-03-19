package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.fee_aggregators.ExactFeeAggregator;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.fee_model.rate.PerDayRate;
import ai.sahaj.utils.Interval;

import static ai.sahaj.VehicleType.BIKE;
import static ai.sahaj.VehicleType.CAR;

public class AirportFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        Rule[] rules = new Rule[]{
            new Rule(new Interval(0, 1), new FlatRate(0), BIKE),
            new Rule(new Interval(1, 8), new FlatRate(40), BIKE),
            new Rule(new Interval(8, 24), new FlatRate(60), BIKE),
            new Rule(new Interval(24, Integer.MAX_VALUE), new PerDayRate(80), BIKE),
            new Rule(new Interval(0, 12), new FlatRate(60), CAR),
            new Rule(new Interval(12, 24), new FlatRate(80), CAR ),
            new Rule(new Interval(24, Integer.MAX_VALUE), new PerDayRate(100), CAR)
        };
        return new ExactFeeAggregator().aggregate(rules, hours, vehicleType);
    }
}

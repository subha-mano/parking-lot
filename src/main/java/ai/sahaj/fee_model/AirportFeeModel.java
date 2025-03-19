package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.fee_model.rate.PerDayRate;
import ai.sahaj.utils.Interval;

import java.util.Arrays;

public class AirportFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        if (vehicleType == VehicleType.BIKE) {
            Rule[] rules = new Rule[]{
                new Rule(new Interval(0, 1), new FlatRate(0)),
                new Rule(new Interval(1, 8), new FlatRate(40)),
                new Rule(new Interval(8, 24), new FlatRate(60)),
                new Rule(new Interval(24, Integer.MAX_VALUE), new PerDayRate(80))
            };

            return fees(rules, hours);
        } else if (vehicleType == VehicleType.CAR) {
            Rule[] rules = new Rule[]{
                    new Rule(new Interval(0, 12), new FlatRate(60)),
                    new Rule(new Interval(12, 24), new FlatRate(80)),
                    new Rule(new Interval(24, Integer.MAX_VALUE), new PerDayRate(100))
            };
            return fees(rules, hours);
        }
        return 0;
    }

    private int fees(Rule[] rules, float hours) {
        return Arrays.stream(rules)
                .filter(rule -> rule.isMatch(hours))
                .findFirst().map(rule -> rule.fees(hours))
                .orElse(0);
    }
}

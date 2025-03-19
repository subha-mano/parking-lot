package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.rate.PerHourRate;
import ai.sahaj.utils.Interval;

import java.util.Arrays;

import static ai.sahaj.VehicleType.*;

public class MallFeeModel implements FeeModel {
    public int fees(VehicleType vehicleType, long minutes) {
        int hours = (int) Math.ceil(minutes / 60.0);
        Rule[] rules = new Rule[]{
            new Rule(new Interval(0, Integer.MAX_VALUE), new PerHourRate(10), BIKE),
            new Rule(new Interval(0, Integer.MAX_VALUE), new PerHourRate(20), CAR),
            new Rule(new Interval(0, Integer.MAX_VALUE), new PerHourRate(50), BUS)
        };
        return fees(rules, hours, vehicleType);
    }

    private int fees(Rule[] rules, float hours, VehicleType vehicleType) {
        return Arrays.stream(rules)
                .filter(rule -> rule.isMatch(hours, vehicleType))
                .findFirst().map(rule -> rule.fees(hours))
                .orElse(0);
    }

}


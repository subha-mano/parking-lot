package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.fee_model.rate.PerHourRate;
import ai.sahaj.utils.Interval;

public class StadiumFeeModel implements FeeModel {
    @Override
    public int fees(VehicleType vehicleType, long minutes) {
        float hours = (float) minutes / 60;
        if (vehicleType.equals(VehicleType.BIKE)) {
            Rule[] rules = new Rule[]{
                    new Rule(new Interval(0, 4), new FlatRate(30)),
                    new Rule(new Interval(4, 12), new FlatRate(60)),
                    new Rule(new Interval(12, Integer.MAX_VALUE), new PerHourRate(100)),
            };

            return getFees(rules, hours);
        } else if (vehicleType.equals(VehicleType.CAR)) {
            Rule[] rules = new Rule[]{
                    new Rule(new Interval(0, 4), new FlatRate(60)),
                    new Rule(new Interval(4, 12), new FlatRate(120)),
                    new Rule(new Interval(12, Integer.MAX_VALUE), new PerHourRate(200)),
            };

            return getFees(rules, hours);
        }
        return 0;
    }

    private int getFees(Rule[] rules, float hours) {
        int fee =0;
        while(hours > 0) {
            for(Rule rule: rules) {
                if(rule.isMatch(hours)){
                    float inclusiveStart = rule.interval.inclusiveStart();
                    fee += rule.fees((float) Math.ceil(hours - inclusiveStart));
                    hours = (float) Math.max(0, inclusiveStart - 0.1);
                }
            }
        }
        return fee;
    }
}

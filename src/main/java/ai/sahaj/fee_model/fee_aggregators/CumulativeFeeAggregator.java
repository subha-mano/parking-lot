package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.Rule;

public class CumulativeFeeAggregator implements FeeAggregator {
    @Override
    public int aggregate(Rule[] rules, float hours, VehicleType vehicleType) {
        int fee = 0;
        while (hours > 0) {
            for (Rule rule : rules) {
                if (rule.isMatch(hours, vehicleType)) {
                    float inclusiveStart = rule.interval.inclusiveStart();
                    fee += rule.fees((float) Math.ceil(hours - inclusiveStart));
                    hours = (float) Math.max(0, inclusiveStart - 0.1);
                }
            }
        }
        return fee;
    }
}

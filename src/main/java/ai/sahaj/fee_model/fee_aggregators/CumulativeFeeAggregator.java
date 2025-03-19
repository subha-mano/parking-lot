package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.Rule;

import java.util.Arrays;

public class CumulativeFeeAggregator implements FeeAggregator {
    @Override
    public int aggregate(Rule[] rules, float hours, VehicleType vehicleType) {
        int fee = 0;
        while (hours > 0) {
            float finalHours = hours;
            Rule rule = Arrays.stream(rules)
                    .filter(r -> r.isMatch(finalHours, vehicleType))
                    .findFirst()
                    .orElse(null);
            if(rule == null) break;
            float inclusiveStart = rule.interval.inclusiveStart();
            fee += rule.fees((float) Math.ceil(hours - inclusiveStart));
            hours = (float) Math.max(0, inclusiveStart - 0.1);
        }
        return fee;
    }
}

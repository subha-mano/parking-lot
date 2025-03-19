package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;

import java.util.Arrays;

public class RuleFeeAggregator {
    int exact(Rule[] rules, float hours, VehicleType vehicleType) {
        return Arrays.stream(rules)
                .filter(rule -> rule.isMatch(hours, vehicleType))
                .findFirst().map(rule -> rule.fees(hours))
                .orElse(0);
    }

    int cumulative(Rule[] rules, float hours, VehicleType vehicleType) {
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

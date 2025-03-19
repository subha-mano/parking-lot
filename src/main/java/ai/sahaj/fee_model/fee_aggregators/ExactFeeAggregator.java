package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.Rule;

import java.util.Arrays;

public class ExactFeeAggregator implements FeeAggregator {
    @Override
    public int aggregate(Rule[] rules, float hours, VehicleType vehicleType) {
        return Arrays.stream(rules)
                .filter(rule -> rule.isMatch(hours, vehicleType))
                .findFirst().map(rule -> rule.fees(hours))
                .orElse(0);
    }
}

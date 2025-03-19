package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.Rule;

public interface FeeAggregator {
    int aggregate(Rule[] rules, float hours, VehicleType vehicleType);
}


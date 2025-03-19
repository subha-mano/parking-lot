package ai.sahaj.fee_model;

import ai.sahaj.VehicleType;
import ai.sahaj.fee_model.rate.Rate;
import ai.sahaj.utils.Interval;

public class Rule {
    public Interval interval;
    Rate rate;
    VehicleType vehicleType;

    public Rule(Interval interval, Rate rate, VehicleType vehicleType) {
        this.interval = interval;
        this.rate = rate;
        this.vehicleType = vehicleType;
    }

    public boolean isMatch(float hours, VehicleType vehicleType) {
        return interval.includes(hours) && vehicleType == this.vehicleType;
    }

    public int fees(float hours){
        return rate.fees(hours);
    }
}

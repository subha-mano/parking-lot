package ai.sahaj.fee_model;

import ai.sahaj.fee_model.rate.Rate;
import ai.sahaj.utils.Interval;

public class Rule {
    Interval interval;
    Rate rate;

    public Rule(Interval interval, Rate rate) {
        this.interval = interval;
        this.rate = rate;
    }

    boolean isMatch(float hours) {
        return interval.includes(hours);
    }

    int fees(float hours){
        return rate.fees(hours);
    }
}

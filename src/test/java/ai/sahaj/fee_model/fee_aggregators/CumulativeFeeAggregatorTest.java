package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.fee_model.Rule;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.utils.Interval;
import org.junit.jupiter.api.Test;

import static ai.sahaj.VehicleType.BIKE;
import static ai.sahaj.VehicleType.CAR;
import static org.junit.jupiter.api.Assertions.*;

class CumulativeFeeAggregatorTest {
    @Test
    void shouldCalculateFeeBasedOnMatchingRuleOfTypeAndHours() {
        CumulativeFeeAggregator cumulativeFeeAggregator = new CumulativeFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
                new Rule(new Interval(12, 24), new FlatRate(20), BIKE),
                new Rule(new Interval(0, 12), new FlatRate(30), CAR),
        };
        assertEquals(10, cumulativeFeeAggregator.aggregate(rules, 4, BIKE));
    }

    @Test
    void shouldCumulateFee() {
        CumulativeFeeAggregator cumulativeFeeAggregator = new CumulativeFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
                new Rule(new Interval(12, 16), new FlatRate(20), BIKE),
                new Rule(new Interval(16, 24), new FlatRate(30), BIKE),
        };
        assertEquals(60, cumulativeFeeAggregator.aggregate(rules, 18, BIKE));
    }

    @Test
    void shouldReturnZeroWhenNoMatch() {
        CumulativeFeeAggregator cumulativeFeeAggregator = new CumulativeFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
        };
        assertEquals(0, cumulativeFeeAggregator.aggregate(rules, 4, CAR));
    }
}
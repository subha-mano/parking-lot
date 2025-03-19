package ai.sahaj.fee_model.fee_aggregators;

import ai.sahaj.fee_model.Rule;
import ai.sahaj.fee_model.rate.FlatRate;
import ai.sahaj.utils.Interval;
import org.junit.jupiter.api.Test;

import static ai.sahaj.VehicleType.BIKE;
import static ai.sahaj.VehicleType.CAR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExactFeeAggregatorTest {
    @Test
    void shouldCalculateFeeBasedOnMatchingRuleOfTypeAndHours() {
        ExactFeeAggregator exactFeeAggregator = new ExactFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
                new Rule(new Interval(12, 24), new FlatRate(20), BIKE),
                new Rule(new Interval(0, 12), new FlatRate(30), CAR),
        };
        assertEquals(10, exactFeeAggregator.aggregate(rules, 4, BIKE));
    }

    @Test
    void shouldCalculateFeeBasedOnFirstMatchingRule() {
        ExactFeeAggregator exactFeeAggregator = new ExactFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
                new Rule(new Interval(0, 12), new FlatRate(20), BIKE),
        };
        assertEquals(10, exactFeeAggregator.aggregate(rules, 4, BIKE));
    }

    @Test
    void shouldReturnZeroWhenNoMatch() {
        ExactFeeAggregator exactFeeAggregator = new ExactFeeAggregator();

        Rule[] rules = {
                new Rule(new Interval(0, 12), new FlatRate(10), BIKE),
        };
        assertEquals(0, exactFeeAggregator.aggregate(rules, 4, CAR));
    }
}
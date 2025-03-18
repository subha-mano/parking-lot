package ai.sahaj.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntervalTest {
    @Test
    void shouldReturnTrueIfWithinInterval() {
        Interval interval = new Interval(1, 2);
        assertTrue(interval.includes(1.5));
    }

    @Test
    void shouldReturnTrueIfStart() {
        Interval interval = new Interval(1, 2);
        assertTrue(interval.includes(1));
    }

    @Test
    void shouldReturnFalseIfEnd() {
        Interval interval = new Interval(1, 2);
        assertFalse(interval.includes(2));
    }

    @Test
    void shouldReturnFalseIfGreaterThanEnd() {
        Interval interval = new Interval(1, 2);
        assertFalse(interval.includes(2.5));
    }

    @Test
    void shouldReturnFalseIfLessThanStart() {
        Interval interval = new Interval(1, 2);
        assertFalse(interval.includes(0.5));
    }
}
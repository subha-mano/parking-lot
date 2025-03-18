package ai.sahaj.utils;

public class Interval {
    private final int inclusiveStart;
    private final int exclusiveEnd;

    public Interval(int inclusiveStart, int exclusiveEnd) {
        this.inclusiveStart = inclusiveStart;
        this.exclusiveEnd = exclusiveEnd;
    }

    public boolean includes(double value) {
        return value >= inclusiveStart && value < exclusiveEnd;
    }
}

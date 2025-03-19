package ai.sahaj.fee_model.rate;

public class PerDayRate implements Rate {
    private final int rate;

    public PerDayRate(int rate) {
        this.rate = rate;
    }

    public int fees(float hours) {
        return (int) Math.ceil(hours / 24.0) * this.rate;
    }
}

package ai.sahaj.fee_model.rate;

public class PerHourRate implements Rate {
    private final int rate;

    public PerHourRate(int rate) {
        this.rate = rate;
    }

    public int fees(float hours) {
        return Math.round(hours) * this.rate;
    }
}

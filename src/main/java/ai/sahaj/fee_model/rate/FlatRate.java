package ai.sahaj.fee_model.rate;

public class FlatRate implements Rate{
    private final int rate;

    public FlatRate(int rate) {
        this.rate = rate;
    }

    public int fees(float hours) {
        return this.rate;
    }
}

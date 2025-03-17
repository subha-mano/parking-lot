package ai.sahaj;

public enum VehicleType {
    BIKE(10),
    CAR(20),
    BUS(50);
    public final long rate;

    VehicleType(long rate) {
        this.rate = rate;
    }
}

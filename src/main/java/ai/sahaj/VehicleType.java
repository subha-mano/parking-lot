package ai.sahaj;

public enum VehicleType {
    BIKE(10),
    CAR(20),
    BUS(50);
    public final int rate;

    VehicleType(int rate) {
        this.rate = rate;
    }
}

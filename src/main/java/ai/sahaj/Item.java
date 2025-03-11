package ai.sahaj;

public class Item {

    public static final int MINIMUM_THRESHOLD = 0;
    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public boolean isQualityGreaterThanMinimumThreshold() {
        return this.quality > MINIMUM_THRESHOLD;
    }
}

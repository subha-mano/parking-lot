package ai.sahaj;

public class Item {

    public static final int MINIMUM_THRESHOLD = 0;
    public static final int MAXIMUM_THRESHOLD = 50;
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

    public boolean isQualityLessThanMaximumThreshold() {
        return this.quality < MAXIMUM_THRESHOLD;
    }

    public void decreaseQuality(int factor) {
        this.quality -= factor;
    }

    public int getQuality() {
        return quality;
    }
}

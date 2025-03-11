package ai.sahaj;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Nested
    class BackstagePassesTests {
        @Test
        void shouldIncrementQualityByOneWhenSellInIsGreaterThanTen() {
            Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 35);
            GildedRose gildedRose = new GildedRose(new Item[]{backstagePasses});
            int expectedQuality = 36;

            gildedRose.updateQuality();
            int actualQuality = backstagePasses.quality;

            assertEquals(expectedQuality, actualQuality);
        }

        @Test
        void shouldIncrementQualityByTwoWhenSellInIsGreaterThanOrEqualToSixAndLessThanOrEqualToTen() {
            Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 7, 35);
            GildedRose gildedRose = new GildedRose(new Item[]{backstagePasses});
            int expectedQuality = 37;

            gildedRose.updateQuality();
            int actualQuality = backstagePasses.quality;

            assertEquals(expectedQuality, actualQuality);
        }

        @Test
        void shouldIncrementQualityByThreeWhenSellInIsGreaterThanOrEqualToOneAndLessThanOrEqualToFive() {
            Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 35);
            GildedRose gildedRose = new GildedRose(new Item[]{backstagePasses});
            int expectedQuality = 38;

            gildedRose.updateQuality();
            int actualQuality = backstagePasses.quality;

            assertEquals(expectedQuality, actualQuality); //Arrange Act Assert
        }

        @Test
        void shouldSetQualityToZeroWhenItemIsExpired() {
            Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 35);
            GildedRose gildedRose = new GildedRose(new Item[]{backstagePasses});
            int expectedQuality = 0;

            gildedRose.updateQuality();
            int actualQuality = backstagePasses.quality;

            assertEquals(expectedQuality, actualQuality);
        }
    }

}

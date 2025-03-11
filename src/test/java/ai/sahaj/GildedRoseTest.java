package ai.sahaj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class GildedRoseTest {
    public static final String NORMAL_ITEM = "Normal";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    void testNormalItemExpiredQualityDecreasesByTwo() {

        Item items[] = createOneItem(NORMAL_ITEM, -1, 2);

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        Item expected = new Item(NORMAL_ITEM, -2, 0);
        assertItems(expected, gildedRose.items[0]);
    }

    @Test
    void testAgedBrieItemExpiredQualityIncreasesByTwo() {
        Item items[] = createOneItem(AGED_BRIE, -1, 2);

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        Item expected = new Item(AGED_BRIE, -2, 4);
        assertItems(expected, gildedRose.items[0]);
    }

    @Test
    void testSulfurasItemExpiredQualityRemainsSame() {
        Item items[] = createOneItem(SULFURAS_HAND_OF_RAGNAROS, -1, 80);

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        Item expected = new Item(SULFURAS_HAND_OF_RAGNAROS, -1, 80);
        assertItems(expected, gildedRose.items[0]);
    }

    private static Item[] createOneItem(String normalItem, int sellIn, int quality) {
        Item item = new Item(normalItem, sellIn, quality);
        Item[] items = {item};

        return items;

    }

    private static void assertItems(Item expected, Item actual) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
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


    private static GildedRose getGildedRoseForNormalItem() {
        return new GildedRose(new Item[]{
                new Item("Normal Item1", 5, 10)
        });
    }

    private static GildedRose getGildedRoseForAgedBrie() {
        return new GildedRose(new Item[]{
                new Item("Aged Brie", 5, 10)
        });
    }

    @Test
    void shouldDecrementQualityBy1ForNormalItem() {
        GildedRose gildedRose = getGildedRoseForNormalItem();

        gildedRose.updateQuality();

        Assertions.assertEquals(9, gildedRose.items[0].quality);
    }

    @Test
    void shouldDecSellInBy1ForAnyItem() {
        GildedRose gildedRose = getGildedRoseForNormalItem();

        gildedRose.updateQuality();

        Assertions.assertEquals(4, gildedRose.items[0].sellIn);
    }

    @Test
    void shouldIncreaseQualityBy1ForAgedBrie() {
        GildedRose agedBrie = getGildedRoseForAgedBrie();

        agedBrie.updateQuality();

        Assertions.assertEquals(11, agedBrie.items[0].quality);
    }
}

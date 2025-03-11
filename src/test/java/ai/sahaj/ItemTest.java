package ai.sahaj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void shouldReturnTrueIfQualityIsGreaterThanMinimumThreshold() {
        Item item = new Item("item", 0, 1);

        assertTrue(item.isQualityGreaterThanMinimumThreshold());
    }

    @Test
    void shouldReturnFalseIfQualityIsLessThanMinimumThreshold() {
        Item item = new Item("item", 0, -1);

        assertFalse(item.isQualityGreaterThanMinimumThreshold());
    }

    @Test
    void shouldReturnFalseIfQualityIsEqualToMinimumThreshold() {
        Item item = new Item("item", 0, 0);

        assertFalse(item.isQualityGreaterThanMinimumThreshold());
    }
    @Test
    void shouldReturnTrueIfQualityIsLessThanMaximumThreshold() {
        Item item = new Item("item", 0, 49);

        assertTrue(item.isQualityLessThanMaximumThreshold());
    }

    @Test
    void shouldReturnFalseIfQualityIsGreaterThanMaximumThreshold() {
        Item item = new Item("item", 0, 51);

        assertFalse(item.isQualityLessThanMaximumThreshold());
    }

    @Test
    void shouldReturnFalseIfQualityIsEqualToMaximumThreshold() {
        Item item = new Item("item", 0, 50);

        assertFalse(item.isQualityLessThanMaximumThreshold());
    }

    @Test
    void shouldDecreaseQualityByGivenFactor() {
        Item item = new Item("item", 0, 10);

        item.decreaseQuality(5);

        assertEquals(5, item.getQuality());
    }
}
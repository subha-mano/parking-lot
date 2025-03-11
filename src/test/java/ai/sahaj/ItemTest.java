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
}
package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void randomArticleLowersBy1() {
        Item[] items = new Item[] { new Item("foo", 1, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals("foo", item.name);
        assertEquals(3, item.quality);
        assertEquals(0, item.sellIn);
    }

    @Test
    void randomArticleLowersBy2AfterOverdue() {
        Item[] items = new Item[] { new Item("foo", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals("foo", item.name);
        assertEquals(2, item.quality);
        assertEquals(-1, item.sellIn);
    }

    @Test
    void aritcleQualityNotNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals("foo", item.name);
        assertEquals(0, item.quality);
        assertEquals(-1, item.sellIn);
    }

    @Test
    void agedBrieIncreasesQuality() {
        Item[] items = new Item[] { new Item(ItemName.AGED_BRIE.label, 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals(ItemName.AGED_BRIE.label, item.name);
        assertEquals(1, item.quality);
        assertEquals(0, item.sellIn);
    }

    @Test
    void agedBrieIncreasesQualityBy2() {
        Item[] items = new Item[] { new Item(ItemName.AGED_BRIE.label, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals(ItemName.AGED_BRIE.label, item.name);
        assertEquals(2, item.quality);
        assertEquals(-1, item.sellIn);
    }

    @Test
    void articleQualityNotAbove50() {
        Item[] items = new Item[] { new Item(ItemName.AGED_BRIE.label, 0, 50), new Item(ItemName.BACKSTAGE_PASS.label, 3, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item agedBrie = app.items[0];
        Item backstagePass = app.items[1];

        assertEquals(ItemName.AGED_BRIE.label, agedBrie.name);
        assertEquals(50, agedBrie.quality);
        assertEquals(-1, agedBrie.sellIn);

        assertEquals(ItemName.BACKSTAGE_PASS.label, backstagePass.name);
        assertEquals(50, backstagePass.quality);
        assertEquals(2, backstagePass.sellIn);
    }

    @Test
    void sulfurasNoChange() {
        Item[] items = new Item[] { new Item(ItemName.SULFURAS.label, 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals(ItemName.SULFURAS.label, item.name);
        assertEquals(80, item.quality);
        assertEquals(0, item.sellIn);
    }

    @Test
    void backstagePasses() {
        Item[] items = new Item[] { new Item(ItemName.BACKSTAGE_PASS.label, 11, 30) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Item item = app.items[0];

        assertEquals(ItemName.BACKSTAGE_PASS.label, item.name);
        assertEquals(31, item.quality);
        assertEquals(10, item.sellIn);

        app.updateQuality();

        assertEquals(ItemName.BACKSTAGE_PASS.label, item.name);
        assertEquals(33, item.quality);
        assertEquals(9, item.sellIn);

        items[0].sellIn = 5;
        app.updateQuality();

        assertEquals(ItemName.BACKSTAGE_PASS.label, item.name);
        assertEquals(36, item.quality);
        assertEquals(4, item.sellIn);

        items[0].sellIn = 0;
        app.updateQuality();

        assertEquals(ItemName.BACKSTAGE_PASS.label, item.name);
        assertEquals(0, item.quality);
        assertEquals(-1, item.sellIn);

        app.updateQuality();

        assertEquals(ItemName.BACKSTAGE_PASS.label, item.name);
        assertEquals(0, item.quality);
        assertEquals(-2, item.sellIn);
    }
}

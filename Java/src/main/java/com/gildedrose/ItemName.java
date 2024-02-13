package com.gildedrose;

public enum ItemName {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),

    CONJURED("Conjured Mana Cake");

    public final String label;

    private ItemName(String label) {
        this.label = label;
    }
}

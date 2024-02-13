package com.gildedrose;

import static com.gildedrose.ItemName.AGED_BRIE;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItems(item);
        }
    }

    void handleItems(Item item) {
        if (ItemName.SULFURAS.label.equals(item.name)) return;
        if (ItemName.AGED_BRIE.label.equals(item.name)) {
            handleAgedBrie(item);
        } else if (ItemName.BACKSTAGE_PASS.label.equals(item.name)) {
            handleBackstagePass(item);
        } else if (ItemName.CONJURED.label.equals(item.name)) {
            handleConjuredItem(item);
        } else {
            handleNormalItem(item);
        }
    }

    void handleAgedBrie(Item item) {
        int normalQualityIncrease = 1;
        if (item.quality < 50) {
            if (item.sellIn > 0) {
                item.quality += normalQualityIncrease;
            } else {
                item.quality += 2 * normalQualityIncrease;
            }
        }
        item.sellIn--;
    }

    void handleBackstagePass(Item item) {
        if(item.quality >= 50) {
            item.sellIn--;
            return;
        }
        if (item.sellIn <= 10 && item.sellIn > 5) {
            item.quality += 2;
        } else if (item.sellIn <= 5 && item.sellIn > 0) {
            item.quality += 3;
        } else if (item.sellIn <= 0) {
            item.quality = 0;
        } else {
            item.quality++;
        }
        item.sellIn--;
    }

    void handleConjuredItem(Item item) {
        int normalQualityReduction = 1;
        if (item.quality > 0) {
            if (item.sellIn > 0) {
                item.quality -= normalQualityReduction;
            } else {
                item.quality -= 2 * normalQualityReduction;
            }
        }
        item.sellIn -= 2;
    }

    void handleNormalItem(Item item) {
        int normalQualityReduction = 1;
        if (item.quality > 0) {
            if (item.sellIn > 0) {
                item.quality -= normalQualityReduction;
            } else {
                item.quality -= 2 * normalQualityReduction;
            }
        }
        item.sellIn--;
    }
}



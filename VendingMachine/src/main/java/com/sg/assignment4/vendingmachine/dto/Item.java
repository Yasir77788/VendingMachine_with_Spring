package com.sg.assignment4.vendingmachine.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Item POJO class
public class Item {
    private String itemName;
    private int itemCost;
    private int numOfItemsInInventory;

    public Item() {
    }

    public Item(String itemName) {

        this.itemName = itemName;

    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCost() {
        return itemCost;
    }

    public int getNumOfItemsInInventory() {
        return numOfItemsInInventory;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public void setNumOfItemsInInventory(int numOfItemsInInventory) {
        this.numOfItemsInInventory = numOfItemsInInventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.itemCost, itemCost) == 0 && numOfItemsInInventory == item.numOfItemsInInventory && Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemCost, numOfItemsInInventory);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemCost=" + itemCost +
                ", numOfItemsInInventory=" + numOfItemsInInventory +
                '}';
    }
}

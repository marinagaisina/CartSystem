package com.marinagaisina;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AppSystem extends TheSystem {
    AppSystem() throws IOException {
        super();
    }

    @Override
    public void display() {
        System.out.println("AppSystem Inventory:");
        System.out.format("%-20s %-20s %-10s %-10s\n", "Name", "Description", "Price", "Available Quantity");
        for (String name : this.getItemCollection().keySet()) {
            Item item = this.getItemCollection().get(name);
            System.out.format("%-20s %-20s %-10s %-10s\n", item.getItemName(), item.getItemDesc(), item.getItemPrice(), item.getAvailableQuantity());
        }
    }

    @Override      // this overwrites the parents class add method
    public Boolean add(Item item) {
        if (item == null) {
            return false;
        }
        for (String name: this.getItemCollection().keySet()) {
            if (name.equals(item.getItemName())) {
                System.out.println("["+ this.getItemCollection().get(item.getItemName())+ "] is already in the App System");
                return false;
            }
        }
        this.getItemCollection().put(item.getItemName(), item);
        return true;
    }

    public Item reduceAvailableQuantity(String item_name) {
        for (String name : this.getItemCollection().keySet()) {
            if (name.equals(item_name)) {
                Item foundItem = this.getItemCollection().get(name);
                if (foundItem.getAvailableQuantity() == 0) {
                    this.getItemCollection().remove(foundItem.getItemName());
                } else {
                    foundItem.setAvailableQuantity(foundItem.getAvailableQuantity() - 1);
                    this.getItemCollection().put(foundItem.getItemName(), foundItem);
                }
                return foundItem;
            }
        }
        return null;
    }
}
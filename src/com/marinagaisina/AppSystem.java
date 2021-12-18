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
        System.out.format("%-20s %-20s %-10s %10s\n", "Name", "Description", "Price", "Available Quantity");
        for (String name : this.getItemCollection().keySet()) {
            System.out.format("%-20s %-20s %-10s %-10s\n", name, this.getItemCollection().get(name).getItemDesc(), this.getItemCollection().get(name).getItemPrice(), this.getItemCollection().get(name).getAvailableQuantity());
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
                foundItem.setAvailableQuantity(foundItem.getAvailableQuantity() - 1);
                return foundItem;
            }
        }
        return null;
    }
}
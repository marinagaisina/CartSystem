package com.marinagaisina;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.copyOf;

public abstract class TheSystem {
private HashMap<String,Item> itemCollection;    //final?

    TheSystem() throws IOException {
        this.itemCollection = new HashMap<>();

        if (this.getClass().getSimpleName().equals("AppSystem")) {
            File readInFile = new File("sample.txt");
            Scanner scanner = new Scanner(readInFile);
            while (scanner.hasNextLine()) {
               // scanner.useDelimiter(" {2}");
                String[] itemInfo = scanner.nextLine().split(" {2}");
                Item item = new Item(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), Integer.parseInt(itemInfo[3]));
                item.setQuantity(1);
                this.getItemCollection().put(item.getItemName(), item);
            }
        }
    }

    public HashMap<String, Item> getItemCollection(){
        return this.itemCollection;
    }

    public Boolean checkAvailability(Item item) {
        if (item.getQuantity() >= item.getAvailableQuantity()) {
            System.out.println("System is unable to add [" + item.getItemName() + "] to the card. System only has [" + item.getAvailableQuantity() + "] [" + item.getItemName() +"]s");
            return false;
        }
        return true;
    }

    public Boolean add(Item item) {
        if (item == null) {
            return false;
        }
        if (this.getItemCollection().size() == 0) {
            this.getItemCollection().put(item.getItemName(), item);
            return true;
        }
        for (String name : this.getItemCollection().keySet()) {
            if (name.equals(item.getItemName())) {
                Item foundItem = this.getItemCollection().get(name);
                foundItem.setQuantity(foundItem.getQuantity()+1);
            } else {
                this.getItemCollection().put(item.getItemName(), item);
            }
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        for (String name : this.getItemCollection().keySet()) {
            if (name.equals(itemName)) {
                Item removeItem = this.getItemCollection().remove(name);
                return removeItem;
            }
        }
        return null;
    }

    public abstract void display();
}

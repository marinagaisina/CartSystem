package com.marinagaisina;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.copyOf;

public abstract class TheSystem {
private Map<String,Item> itemCollection;    //final??

    TheSystem() throws IOException {
        this.itemCollection = new HashMap<>();

        if (this.getClass().getSimpleName().equals("AppSystem")) {
            File readInFile = new File("sample.txt");
            Scanner scanner = new Scanner(readInFile);
            while (scanner.hasNextLine()) {
                String[] itemInfo = scanner.nextLine().split("\\s+");
                Item item = new Item(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), Integer.parseInt(itemInfo[3]));
                this.getItemCollection().put(item.getItemName(), item);
            }
        }
    }

    public HashMap<String, Item> getItemCollection(){
        return (HashMap<String, Item>) this.itemCollection;
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
        for (String name: this.getItemCollection().keySet()) {
            if (name.equals(item.getItemName())) {
                Item foundItem = this.getItemCollection().get(name);
                foundItem.setQuantity(foundItem.getQuantity()+1);
            } else {
                itemCollection.put(item.getItemName(), item);
            }
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        for (String name : this.getItemCollection().keySet()) {
            if (name.equals(itemName)) {
                return this.getItemCollection().remove(name);
            }
        }
        return null;
    }

    public abstract void display();
}

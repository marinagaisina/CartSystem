package com.marinagaisina;

import java.io.IOException;

public class CartSystem extends TheSystem {
    CartSystem() throws IOException {
        super();
    }

    @Override
    public void display() {
        double preTax = 0D;
        System.out.println("Cart:");
        System.out.format("%-20s %-20s %-10s %-10s %-10s\n", "Name", "Description", "Price", "Quantity", "Sub Total");
        for (String name : this.getItemCollection().keySet()) {
            Item item = this.getItemCollection().get(name);
            preTax += item.getItemPrice()*item.getQuantity();
            System.out.printf("%-20s %-20s %-10s %-10s %-10s\n", name, item.getItemDesc(), item.getItemPrice(), item.getQuantity(),item.getQuantity()*item.getItemPrice());
        }
        System.out.format("%-20s %20.2f\n", "Pre-tax Total", preTax);
        System.out.format("%-20s %20.2f\n", "Tax", preTax*0.05);
        System.out.format("%-20s %20.2f\n", "Total", preTax+preTax*0.05);
    }
}
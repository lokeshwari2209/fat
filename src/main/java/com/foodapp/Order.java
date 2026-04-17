package com.foodapp;

import java.util.Objects;
import java.util.UUID;

public class Order {
    private final String id;
    private final String item;
    private final int quantity;

    public Order(String item, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
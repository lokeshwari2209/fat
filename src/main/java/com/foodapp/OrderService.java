package com.foodapp;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public Order createOrder(String item, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        Order o = new Order(item, qty);
        orders.put(o.getId(), o);
        return o;
    }

    public boolean cancelOrder(String orderId) {
        return orders.remove(orderId) != null;
    }

    public Optional<Order> getOrder(String orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }
}
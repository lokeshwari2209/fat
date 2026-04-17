package com.foodapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testOrderCreateAndCancel() {
        OrderService svc = new OrderService();
        Order o = svc.createOrder("Pizza", 2);
        assertNotNull(o.getId());
        assertTrue(svc.getOrder(o.getId()).isPresent());
        assertTrue(svc.cancelOrder(o.getId()));
        assertFalse(svc.getOrder(o.getId()).isPresent());
    }
}
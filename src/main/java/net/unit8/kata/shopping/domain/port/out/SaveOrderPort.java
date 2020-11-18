package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Order;

public interface SaveOrderPort {
    void save(Order order);
}

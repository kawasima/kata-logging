package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Cart;

public interface ClearCartItemPort {
    void clear(Cart cart);
}

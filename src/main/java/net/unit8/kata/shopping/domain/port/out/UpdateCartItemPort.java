package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.Product;

public interface UpdateCartItemPort {
    void addItem(Cart cart, Product product, int amount);
}

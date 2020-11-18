package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Cart;

public interface LoadCartPort {
    Cart load(Long accountId);
}

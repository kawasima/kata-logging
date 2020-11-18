package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Product;

public interface SaveProductPort {
    void save(Product product);
}

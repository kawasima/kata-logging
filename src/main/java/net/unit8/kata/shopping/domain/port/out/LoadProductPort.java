package net.unit8.kata.shopping.domain.port.out;

import net.unit8.kata.shopping.domain.Product;

public interface LoadProductPort {
    Product load(String productCd);
}

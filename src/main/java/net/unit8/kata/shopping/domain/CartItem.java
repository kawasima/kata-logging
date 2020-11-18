package net.unit8.kata.shopping.domain;

import javax.money.MonetaryAmount;
import java.io.Serializable;

public class CartItem implements Serializable {
    private final Product product;
    private final int amount;

    public CartItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public OrderLine checkout() {
        return new OrderLine(product, product.getPrice(), amount);
    }

    public MonetaryAmount price() {
        return product.getPrice().multiply(amount);
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}

package net.unit8.kata.shopping.domain;

import javax.money.MonetaryAmount;

public class OrderLine {
    private Product product;
    private MonetaryAmount price;
    private int amount;

    public OrderLine(Product product, MonetaryAmount price, int amount) {
        this.product = product;
        this.price = price;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}

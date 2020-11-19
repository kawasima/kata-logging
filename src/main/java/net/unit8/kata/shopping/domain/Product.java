package net.unit8.kata.shopping.domain;

import javax.money.MonetaryAmount;
import java.io.Serializable;

public class Product implements Serializable {
    private final String code;
    private final String name;
    private final MonetaryAmount price;


    public Product(String code, String name, MonetaryAmount price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public MonetaryAmount getPrice() {
        return price;
    }
}

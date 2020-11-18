package net.unit8.kata.shopping.web;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String productCd;
    private int amount;

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

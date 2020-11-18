package net.unit8.kata.shopping.persistence;

import java.io.Serializable;

public class OrderLinePk implements Serializable {
    private Long orderId;
    private Long productId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

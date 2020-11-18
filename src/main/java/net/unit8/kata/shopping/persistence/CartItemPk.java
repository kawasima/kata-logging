package net.unit8.kata.shopping.persistence;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartItemPk implements Serializable {
    private Long cartId;
    private Long productId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

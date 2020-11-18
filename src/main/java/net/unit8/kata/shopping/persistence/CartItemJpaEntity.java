package net.unit8.kata.shopping.persistence;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_items")
@IdClass(CartItemPk.class)
public class CartItemJpaEntity implements Serializable {
    @Id
    @Column(name = "cart_id", nullable = false, insertable = false, updatable = false)
    private Long cartId;

    @Id
    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Long productId;

    @MapsId("cartId")
    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "cart_id")
    private CartJpaEntity cart;

    @MapsId("productId")
    @ManyToOne(cascade = { CascadeType.REMOVE }, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductJpaEntity product;

    private int amount;

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

    public CartJpaEntity getCart() {
        return cart;
    }

    public void setCart(CartJpaEntity cart) {
        this.cart = cart;
        this.cartId = cart.getId();
    }

    public ProductJpaEntity getProduct() {
        return product;
    }

    public void setProduct(ProductJpaEntity product) {
        this.product = product;
        this.productId = product.getId();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItemJpaEntity{" +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }
}

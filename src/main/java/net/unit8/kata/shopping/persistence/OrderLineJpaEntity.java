package net.unit8.kata.shopping.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "order_lines")
@IdClass(OrderLinePk.class)
public class OrderLineJpaEntity implements Serializable {
    @Id
    @Column(name = "order_id", nullable = false, insertable = false, updatable = false)
    private Long orderId;

    @Id
    @Column(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Long productId;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    private int amount;
    private BigDecimal price;

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

    public OrderJpaEntity getOrder() {
        return order;
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

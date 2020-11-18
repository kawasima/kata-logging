package net.unit8.kata.shopping.persistence;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customer;

    private String locale;
    private BigDecimal totalPayment;
    private String currencyCode;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
    private List<OrderLineJpaEntity> orderLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerJpaEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerJpaEntity customer) {
        this.customer = customer;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public List<OrderLineJpaEntity> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineJpaEntity> orderLines) {
        this.orderLines = orderLines;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

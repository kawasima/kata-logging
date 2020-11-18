package net.unit8.kata.shopping.domain;

import javax.money.MonetaryAmount;
import java.util.List;

public class Order {
    private MonetaryAmount payment;
    private List<OrderLine> orderLine;
    private Customer customer;

    public Order(Customer customer, List<OrderLine> orderLine, MonetaryAmount payment) {
        this.customer = customer;
        this.orderLine = orderLine;
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public MonetaryAmount getPayment() {
        return payment;
    }

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }
}

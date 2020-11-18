package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Order;
import net.unit8.kata.shopping.domain.OrderLine;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final CustomerMapper customerMapper;
    private final ProductMapper productMapper;

    public OrderMapper(CustomerMapper customerMapper, ProductMapper productMapper) {
        this.customerMapper = customerMapper;
        this.productMapper = productMapper;
    }

    public Order mapEntityToDomain(OrderJpaEntity orderEntity) {
        return new Order(
                customerMapper.mapEntityToDomain(orderEntity.getCustomer()),
                orderEntity.getOrderLines().stream()
                        .map(e -> new OrderLine(
                                productMapper.mapEntityToDomain(e.getProduct()),
                                Monetary.getDefaultAmountFactory()
                                        .setCurrency("USD")
                                        .setNumber(e.getPrice())
                                        .create(),
                                e.getAmount()
                        )).collect(Collectors.toList()),
                Monetary.getDefaultAmountFactory()
                        .setCurrency(orderEntity.getCurrencyCode())
                        .setNumber(orderEntity.getTotalPayment())
                        .create()
        );
    }

    public OrderJpaEntity mapDomainToEntity(Order order) {
        OrderJpaEntity orderEntity = new OrderJpaEntity();
        orderEntity.setCustomer(
                customerMapper.mapDomainToEntity(order.getCustomer())
        );
        return orderEntity;
    }
}

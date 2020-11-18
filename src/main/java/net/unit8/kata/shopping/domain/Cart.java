package net.unit8.kata.shopping.domain;

import net.unit8.kata.shopping.domain.port.out.ClearCartItemPort;
import net.unit8.kata.shopping.domain.port.out.SaveOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cart implements Serializable {
    private final Long id;
    private final Set<CartItem> items;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SaveOrderPort saveOrderPort;
    @Autowired
    private ClearCartItemPort clearCartItemPort;
    @Autowired
    private TransactionTemplate transactionTemplate;

    private Cart(Long id, Set<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    protected static Cart withId(Long id, Set<CartItem> items) {
        return new Cart(id, items);
    }

    protected static Cart withoutId(Set<CartItem> items) {
        return new Cart(null, items);
    }

    public Long getId() {
        return id;
    }

    public Stream<CartItem> cartItemStream() {
        return items.stream();
    }

    @Transactional
    public Order checkout(Customer customer) {
        String currencyCode = Currency.getInstance(customer.getLocale()).getCurrencyCode();
        BigDecimal total = items.stream()
                .map(CartItem::price)
                .map(price -> {
                    MonetaryAmount amount = restTemplate.getForObject("http://localhost:8081/trade?amount={amount}&fromCurrency={fromCurrency}&toCurrency={toCurrency}",
                            MonetaryAmount.class,
                            Map.of("amount", price.getNumber().numberValue(BigDecimal.class).toPlainString(),
                                    "fromCurrency", price.getCurrency().getCurrencyCode(),
                                    "toCurrency", currencyCode));
                    return amount.getNumber().numberValue(BigDecimal.class);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order(customer,
                items.stream().map(CartItem::checkout).collect(Collectors.toList()),
                Monetary.getDefaultAmountFactory()
                        .setCurrency(currencyCode)
                        .setNumber(total)
                        .create()
        );

        transactionTemplate.executeWithoutResult(status -> {
            saveOrderPort.save(order);
            clearCartItemPort.clear(this);
        });

        return order;
    }
}

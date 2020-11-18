package net.unit8.kata.shopping.web;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.CartItem;
import net.unit8.kata.shopping.domain.Customer;
import net.unit8.kata.shopping.domain.Order;
import net.unit8.kata.shopping.domain.port.out.LoadCartPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);
    private final LoadCartPort loadCartPort;

    public CartController(LoadCartPort loadCartPort) {
        this.loadCartPort = loadCartPort;
    }

    @GetMapping
    public Set<CartItem> findCart() {
        Customer customer = new Customer(1L, Locale.JAPAN);
        Cart cart = loadCartPort.load(customer.getId());
        return cart.cartItemStream().collect(Collectors.toSet());
    }

    @PutMapping("/checkout")
    public Order checkout() {
        Customer customer = new Customer(1L, Locale.JAPAN);
        Cart cart = loadCartPort.load(customer.getId());
        Order order = cart.checkout(customer);
        return order;
    }
}

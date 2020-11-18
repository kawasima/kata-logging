package net.unit8.kata.shopping.web;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.LoadCartPort;
import net.unit8.kata.shopping.domain.port.out.LoadProductPort;
import net.unit8.kata.shopping.domain.port.out.UpdateCartItemPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/items")
public class CartItemController {
    private final LoadProductPort loadProductPort;
    private final LoadCartPort loadCartPort;
    private final UpdateCartItemPort updateCartItemPort;
    public CartItemController(LoadProductPort loadProductPort, LoadCartPort loadCartPort, UpdateCartItemPort updateCartItemPort) {
        this.loadProductPort = loadProductPort;
        this.loadCartPort = loadCartPort;
        this.updateCartItemPort = updateCartItemPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postItem(@RequestBody CartItem item) {
        Product product = loadProductPort.load(item.getProductCd());
        Cart cart = loadCartPort.load(1L);
        updateCartItemPort.addItem(cart, product, item.getAmount());
    }
}

package net.unit8.kata.web;

import net.unit8.kata.shopping.domain.CartFactory;
import net.unit8.kata.shopping.domain.CartItem;
import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.LoadCartPort;
import net.unit8.kata.shopping.web.CartController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.StaticApplicationContext;

import javax.money.Monetary;
import java.util.Set;

import static org.mockito.Mockito.*;

class CartControllerTest {
    StaticApplicationContext context;

    @BeforeEach
    void setup() {
         context = new StaticApplicationContext();
         context.registerBean(CartController.class);
         context.registerBean(CartFactory.class);
         context.registerBean(DefaultListableBeanFactory.class);
         context.registerBean(LoadCartPort.class, this::mockLoadCartPort);
    }

    @Test
    void test() {
        CartController controller = context.getBean(CartController.class);
        controller.findCart();
        verify(context.getBean(LoadCartPort.class), times(1)).load(anyLong());
    }

    LoadCartPort mockLoadCartPort() {
        LoadCartPort mock = mock(LoadCartPort.class);
        CartFactory cartFactory = context.getBean(CartFactory.class);
        doReturn(cartFactory.create(
                Set.of(
                        new CartItem(
                                new Product("P001", "Product X", Monetary.getDefaultAmountFactory().setNumber(100).setCurrency("JPY").create()),
                                10
                        )
                )
        )).when(mock).load(anyLong());

        return mock;
    }
}

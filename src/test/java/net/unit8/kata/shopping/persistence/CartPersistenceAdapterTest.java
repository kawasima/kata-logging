package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.CartFactory;
import net.unit8.kata.shopping.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.money.Monetary;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({
        CartPersistenceAdapter.class,
        OrderPersistenceAdapter.class,
        ProductPersistenceAdapter.class,
        CartMapper.class,
        CartItemMapper.class,
        ProductMapper.class,
        CartFactory.class
})
class CartPersistenceAdapterTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private CartPersistenceAdapter adapter;

    @Autowired
    private ProductPersistenceAdapter productPersistenceAdapter;

    @Autowired
    private CartRepository repository;

    @Test
    void loadCart() {
        Cart cart = adapter.load(1L);
        assertThat(cart).isNotNull();
        Product product = new Product("P1234", "Product X",
                Monetary.getDefaultAmountFactory()
                        .setCurrency("USD")
                        .setNumber(100)
                        .create());
        productPersistenceAdapter.save(product);
        adapter.addItem(cart, product, 1);
    }
}
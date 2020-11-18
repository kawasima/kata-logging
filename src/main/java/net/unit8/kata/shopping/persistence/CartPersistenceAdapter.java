package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.ClearCartItemPort;
import net.unit8.kata.shopping.domain.port.out.LoadCartPort;
import net.unit8.kata.shopping.domain.port.out.UpdateCartItemPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class CartPersistenceAdapter implements LoadCartPort, UpdateCartItemPort, ClearCartItemPort {
    private static final Logger LOG = LoggerFactory.getLogger(CartPersistenceAdapter.class);

    public final CartRepository cartRepository;
    public final ProductRepository productRepository;
    public final CartMapper cartMapper;
    public final CartItemMapper cartItemMapper;

    @Override
    public Cart load(Long userId) {
        CartJpaEntity cartEntity = cartRepository.findByUserId(userId);
        if (cartEntity == null) {
            cartEntity = new CartJpaEntity();
            cartEntity.setUserId(userId);
            cartEntity = cartRepository.save(cartEntity);
        }
        return cartMapper.mapEntityToDomain(cartEntity);
    }

    @Override
    public void addItem(Cart cart, Product product, int amount) {
        CartJpaEntity cartEntity = cartRepository.getOne(cart.getId());
        ProductJpaEntity productEntity = productRepository.findByCode(product.getCode());
        CartItemJpaEntity cartItemEntity = new CartItemJpaEntity();
        cartItemEntity.setCart(cartEntity);
        cartItemEntity.setProduct(productEntity);
        cartItemEntity.setAmount(amount);
        cartEntity.getItems().add(cartItemEntity);
        LOG.info("cart = {}", cartEntity);
        cartRepository.saveAndFlush(cartEntity);
    }

    @Override
    public void clear(Cart cart) {
        CartJpaEntity cartEntity = cartRepository.getOne(cart.getId());
        cartEntity.getItems().clear();
        LOG.info("cart=clear:{}", cartEntity);
        cartRepository.saveAndFlush(cartEntity);
    }

    public CartPersistenceAdapter(CartRepository cartRepository,
                                  ProductRepository productRepository, CartMapper cartMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
    }
}

package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Cart;
import net.unit8.kata.shopping.domain.CartFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CartMapper {
    private final CartItemMapper cartItemMapper;
    private final CartFactory cartFactory;

    public CartMapper(CartItemMapper cartItemMapper, CartFactory cartFactory) {
        this.cartItemMapper = cartItemMapper;
        this.cartFactory = cartFactory;
    }

    public Cart mapEntityToDomain(CartJpaEntity cartEntity) {
        return cartFactory.withId(cartEntity.getId())
                .create(Optional.ofNullable(cartEntity.getItems())
                        .map(items -> items.stream().map(cartItemMapper::mapEntityToDomain))
                        .orElse(Stream.empty())
                .collect(Collectors.toSet()));
    }

    public CartJpaEntity mapDomainToEntity(Cart cart) {
        CartJpaEntity cartEntity = new CartJpaEntity();
        cartEntity.setItems(cart
                .cartItemStream()
                .map(cartItemMapper::mapDomainToEntity)
                .collect(Collectors.toList()));
        cartEntity.setId(cart.getId());
        return cartEntity;
    }
}

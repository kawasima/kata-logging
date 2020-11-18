package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.CartItem;
import org.springframework.stereotype.Component;

@Component
class CartItemMapper {
    private final ProductMapper productMapper;

    public CartItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CartItem mapEntityToDomain(CartItemJpaEntity cartItemEntity) {
        return new CartItem(productMapper.mapEntityToDomain(cartItemEntity.getProduct()), cartItemEntity.getAmount());
    }

    public CartItemJpaEntity mapDomainToEntity(CartItem item) {
        CartItemJpaEntity cartItemEntity = new CartItemJpaEntity();
        cartItemEntity.setProduct(productMapper.mapDomainToEntity(item.getProduct()));
        cartItemEntity.setAmount(item.getAmount());
        return cartItemEntity;
    }
}

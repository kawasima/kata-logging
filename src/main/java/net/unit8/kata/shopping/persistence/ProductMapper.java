package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Product;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import java.math.BigDecimal;

@Component
class ProductMapper {
    public Product mapEntityToDomain(ProductJpaEntity productEntity) {

        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                Monetary.getDefaultAmountFactory()
                        .setNumber(productEntity.getPrice())
                        .setCurrency(productEntity.getCurrencyCode())
                        .create());
    }

    public ProductJpaEntity mapDomainToEntity(Product product) {
        ProductJpaEntity productEntity = new ProductJpaEntity();
        productEntity.setCode(product.getCode());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice().getNumber().numberValue(BigDecimal.class));
        productEntity.setCurrencyCode(product.getPrice().getCurrency().getCurrencyCode());
        return productEntity;
    }
}

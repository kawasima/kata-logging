package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.LoadProductPort;
import net.unit8.kata.shopping.domain.port.out.SaveProductPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort {
    private static final Logger LOG = LoggerFactory.getLogger(ProductPersistenceAdapter.class);

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductPersistenceAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product load(String productCd) {
        ProductJpaEntity productEntity = productRepository.findByCode(productCd);
        if (productEntity == null) {
            return null;
        }
        return productMapper.mapEntityToDomain(productEntity);
    }

    @Override
    public void save(Product product) {
        ProductJpaEntity productEntity = productMapper.mapDomainToEntity(product);
        productRepository.save(productEntity);
    }
}

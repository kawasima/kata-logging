package net.unit8.kata.shopping.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    ProductJpaEntity findByCode(String code);
}

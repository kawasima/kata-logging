package net.unit8.kata.shopping.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CartRepository extends JpaRepository<CartJpaEntity, Long> {
    CartJpaEntity findByUserId(Long userId);
}

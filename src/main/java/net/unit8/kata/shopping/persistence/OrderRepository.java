package net.unit8.kata.shopping.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, Long> {
}

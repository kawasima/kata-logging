package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Order;
import net.unit8.kata.shopping.domain.port.out.SaveOrderPort;
import org.springframework.stereotype.Component;

@Component
class OrderPersistenceAdapter implements SaveOrderPort {
    @Override
    public void save(Order order) {

    }
}

package net.unit8.kata.shopping.persistence;

import net.unit8.kata.shopping.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CustomerMapper {
    public Customer mapEntityToDomain(CustomerJpaEntity customerEntity) {
        return new Customer(
                customerEntity.getId(),
                new Locale(customerEntity.getLocale()));
    }

    public CustomerJpaEntity mapDomainToEntity(Customer customer) {
        CustomerJpaEntity customerEntity = new CustomerJpaEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setLocale(customer.getLocale().toString());
        return customerEntity;
    }
}

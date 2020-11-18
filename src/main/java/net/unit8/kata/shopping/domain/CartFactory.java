package net.unit8.kata.shopping.domain;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CartFactory {
    private final AutowireCapableBeanFactory beanFactory;

    public CartFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public CartBuilder withId(Long id) {
        CartBuilder builder = new CartBuilder(beanFactory);
        return builder.withId(id);
    }

    public Cart create(Set<CartItem> items) {
        Cart cart = Cart.withoutId(items);
        beanFactory.autowireBean(cart);
        return cart;
    }

    public static class CartBuilder {
        private Long id;
        private AutowireCapableBeanFactory beanFactory;

        CartBuilder(AutowireCapableBeanFactory beanFactory) {
            this.beanFactory = beanFactory;
        }

        public CartBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public Cart create(Set<CartItem> items) {
            Cart cart =  Cart.withId(id, items);
            beanFactory.autowireBean(cart);
            return cart;
        }
    }
}

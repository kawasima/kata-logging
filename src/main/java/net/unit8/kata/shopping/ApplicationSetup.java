package net.unit8.kata.shopping;

import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.SaveProductPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.money.Monetary;

@Component
public class ApplicationSetup implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationSetup.class);

    private final SaveProductPort saveProductPort;

    public ApplicationSetup(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Product product1 = new Product("P001", "Product A",
                Monetary.getDefaultAmountFactory().setNumber(500).setCurrency("USD").create());
        Product product2 = new Product("P002", "Product B",
                Monetary.getDefaultAmountFactory().setNumber(5000).setCurrency("CZK").create());
        Product product3 = new Product("P003", "Product C",
                Monetary.getDefaultAmountFactory().setNumber(500).setCurrency("EUR").create());
        LOG.info("Application Setup...");
        saveProductPort.save(product1);
        saveProductPort.save(product2);
        saveProductPort.save(product3);
    }
}

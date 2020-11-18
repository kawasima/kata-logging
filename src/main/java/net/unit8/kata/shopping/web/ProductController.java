package net.unit8.kata.shopping.web;

import net.unit8.kata.shopping.domain.Product;
import net.unit8.kata.shopping.domain.port.out.LoadProductPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private final LoadProductPort loadProductPort;

    public ProductController(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }

    @GetMapping("/{productCd}")
    public Product findAll(@PathVariable("productCd") String productCd) {
        return loadProductPort.load(productCd);
    }
}

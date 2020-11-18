package net.unit8.kata.shopping.domain;

import java.util.Locale;

public class Customer {
    private Long id;
    private Locale locale;

    public Customer(Long id, Locale locale) {
        this.id = id;
        this.locale = locale;
    }
    public Locale getLocale() {
        return locale;
    }

    public Long getId() {
        return id;
    }
}

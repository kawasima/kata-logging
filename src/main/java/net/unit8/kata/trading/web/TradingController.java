package net.unit8.kata.trading.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

@RestController
public class TradingController {
    private static final Logger LOG = LoggerFactory.getLogger(TradingController.class);

    @GetMapping("/trade")
    public MonetaryAmount rate(@RequestParam double amount,
                               @RequestParam String fromCurrency,
                               @RequestParam String toCurrency) {
        MDC.put("amount", String.valueOf(amount));
        MonetaryAmount money = Monetary.getDefaultAmountFactory()
                .setNumber(amount)
                .setCurrency(fromCurrency)
                .create();
        CurrencyConversion conversion = MonetaryConversions.getConversion(toCurrency);
        return money.with(conversion);
    }
}

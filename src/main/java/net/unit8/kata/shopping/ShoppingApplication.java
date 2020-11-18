package net.unit8.kata.shopping;

import net.unit8.kata.shared.LoggingAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.zalando.jackson.datatype.money.MoneyModule;
import org.zalando.problem.ProblemModule;

import java.util.Collections;

@SpringBootApplication
public class ShoppingApplication {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.modules(
                new MoneyModule().withQuotedDecimalNumbers(),
                new ProblemModule());
        return builder;
    }

    @Bean
    public LoggingAdvice loggingAdvice() {
        return new LoggingAdvice();
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShoppingApplication.class);
        app.setDefaultProperties(Collections.singletonMap(
                "server.port", "8080"
        ));
        app.run(args);
    }
}

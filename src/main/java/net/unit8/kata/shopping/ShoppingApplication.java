package net.unit8.kata.shopping;

import net.unit8.kata.shared.GlobalControllerExceptionHandler;
import net.unit8.kata.shared.LoggingAdvice;
import net.unit8.kata.shared.RestTemplateLoggingInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.zalando.jackson.datatype.money.MoneyModule;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.spring.web.advice.general.ThrowableAdviceTrait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public ThrowableAdviceTrait globalControllerExceptionHandler() {
        return new GlobalControllerExceptionHandler();
    }

    @Bean
    public LoggingAdvice loggingAdvice() {
        return new LoggingAdvice();
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofSeconds(1))
                .setReadTimeout(Duration.ofSeconds(3))
                .build();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShoppingApplication.class);
        app.setDefaultProperties(Collections.singletonMap(
                "server.port", "8080"
        ));
        app.run(args);
    }
}

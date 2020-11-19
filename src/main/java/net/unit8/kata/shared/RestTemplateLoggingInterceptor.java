package net.unit8.kata.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger("call-external");
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        try {
            ClientHttpResponse response = execution.execute(request, body);
            traceResponse(response);
            return response;
        } catch(SocketTimeoutException e) {
            throw new RuntimeException("API call timeout: " + e.getMessage());
        }
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        MDC.put("uri", request.getURI().toString());
        MDC.put("method", Optional.ofNullable(request.getMethod())
                .map(HttpMethod::name)
                .orElse("")
        );
        MDC.put("headers", request.getHeaders().toString());
        MDC.put("body", new String(body, StandardCharsets.UTF_8));
        LOG.info("request");
        MDC.remove("uri");
        MDC.remove("method");
        MDC.remove("headers");
        MDC.remove("body");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        MDC.put("status", Integer.toString(response.getStatusCode().value()));
        MDC.put("headers", response.getHeaders().toString());
        LOG.info("response");
        MDC.remove("status");
        MDC.remove("headers");
    }
}

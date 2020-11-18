package net.unit8.kata.shopping.web;

import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TraceIdInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = Optional.ofNullable(request.getAttribute("traceId"))
                .map(Objects::toString)
                .orElseGet(() -> {
                    String id = UUID.randomUUID().toString();
                    request.setAttribute("traceId", id);
                    return id;
                });
        MDC.put("traceId", traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove("traceId");
    }
}

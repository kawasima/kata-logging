package net.unit8.kata.shared;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Aspect
public class LoggingAdvice {
    private final Logger logger = LoggerFactory.getLogger("tracing");

    @Before("execution(* net.unit8.kata.*.*.*.*(..))")
    public void invokeApiControllerBefore(JoinPoint joinPoint) {
        outputLog(joinPoint);
    }


    @AfterReturning(pointcut = "execution(* net.unit8.kata.*.*.*.*(..))", returning = "returnValue")
    public void invokeControllerAfter(JoinPoint joinPoint, Object returnValue) {
        outputLog(joinPoint, returnValue);
    }

    private void outputLog(JoinPoint joinPoint) {
        MDC.put("class", getClassName(joinPoint));
        MDC.put("method", getSignatureName(joinPoint));
        MDC.put("args", getArguments(joinPoint));
        logger.info("Enter");
        MDC.remove("class");
        MDC.remove("method");
        MDC.remove("args");
    }

    private void outputLog(JoinPoint joinPoint, Object returnValue) {
        MDC.put("class", getClassName(joinPoint));
        MDC.put("method", getSignatureName(joinPoint));
        MDC.put("return", getReturnValue(returnValue));
        logger.info("Exit");
        MDC.remove("class");
        MDC.remove("method");
        MDC.remove("return");
    }

    private String getTraceId() {
        return Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()))
                .map(ServletRequestAttributes::getRequest)
                .map(req -> req.getAttribute("traceId"))
                .map(Object::toString)
                .orElse("");
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().toString();
    }

    private String getSignatureName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String getArguments(JoinPoint joinPoint) {
        if (joinPoint.getArgs() == null) {
            return "argument is null";
        }

        Object[] arguments = joinPoint.getArgs();
        List<String> argumentStrings = new ArrayList<>();

        for (Object argument : arguments) {
            if (argument != null) {
                argumentStrings.add(argument.toString());
            }
        }
        return String.join(",", argumentStrings);
    }

    private String getReturnValue(Object returnValue) {
        return (returnValue != null) ? returnValue.toString() : "return value is null";
    }

}
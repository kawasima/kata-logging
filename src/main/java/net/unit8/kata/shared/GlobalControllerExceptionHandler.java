package net.unit8.kata.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.general.ThrowableAdviceTrait;

@ControllerAdvice
public class GlobalControllerExceptionHandler implements ThrowableAdviceTrait {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    public ResponseEntity<Problem> handleThrowable(
            final Throwable throwable,
            final NativeWebRequest request) {
        LOG.error("error", throwable);
        return ResponseEntity.status(500).body(
                Problem.builder()
                        .withTitle(Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .withStatus(Status.INTERNAL_SERVER_ERROR)
                        .withDetail(throwable.getMessage())
                        .build()
        );
    }
}

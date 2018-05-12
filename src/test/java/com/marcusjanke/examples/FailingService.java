package com.marcusjanke.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Failing service, tracks attempts for convenience, provides recovery
 */
@Service
public class FailingService {

    private final Logger logger = LoggerFactory.getLogger(FailingService.class);

    private final ThreadLocal<Integer> attempts = new ThreadLocal<>();
    private final ThreadLocal<Boolean> recovered = new ThreadLocal<>();

    public FailingService() {
        attempts.set(0);
        recovered.set(false);
    }

    @Retryable(
            value = { RetryableException.class },
            maxAttempts = 2)
    void doWork() throws RetryableException {
        logger.info("doing work");
        attempts.set(attempts.get() + 1);
        throw new RetryableException();
    }

    @Recover
    public void recover(RetryableException e) {
        logger.info("recovering");
        recovered.set(true);
    }

    Integer getAttempts() {
        return attempts.get();
    }

    Boolean getRecovered() {
        return recovered.get();
    }
}
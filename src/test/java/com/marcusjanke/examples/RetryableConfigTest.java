package com.marcusjanke.examples;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {RetryableConfig.class, FailingService.class },
        loader = AnnotationConfigContextLoader.class)
public class RetryableConfigTest {

    @Autowired
    private FailingService failingService;

    @Test
    public void callWithExceptionAndRetry() throws RetryableException {
        int initialAttempts = failingService.getAttempts();
        assertThat(failingService.getRecovered()).isFalse();
        failingService.doWork();
        int finalAttempts = failingService.getAttempts();
        assertThat(finalAttempts - initialAttempts).isEqualTo(2);
        assertThat(failingService.getRecovered()).isTrue();
    }
}

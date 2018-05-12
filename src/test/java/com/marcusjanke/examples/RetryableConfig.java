package com.marcusjanke.examples;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Enable retry configuration
 */
@Configuration
@EnableRetry
class RetryableConfig {

}

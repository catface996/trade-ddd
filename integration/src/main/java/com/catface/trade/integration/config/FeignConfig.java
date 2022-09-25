package com.catface.trade.integration.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author catface
 * @since 2022/9/25
 */
@Configuration
@EnableFeignClients(basePackages = {
    "com.catface.pay.api"
})
public class FeignConfig {

}

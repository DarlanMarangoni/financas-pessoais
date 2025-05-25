package com.darlanmarangoni.financas.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "com.darlanmarangoni.financas.application",
    "com.darlanmarangoni.financas.domain",
    "com.darlanmarangoni.financas.infrastructure"
})
public class BeanConfiguration {
    // Additional bean configurations if needed
}

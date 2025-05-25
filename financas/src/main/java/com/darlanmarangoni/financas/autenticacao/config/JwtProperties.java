package com.darlanmarangoni.financas.autenticacao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.security.jwt")
public class JwtProperties {
    private String secretKey = "1F4F1625EF45A12CD4B37FF8D91C4133D6BC95A4E7D346BC90B0D0FC456D2F11";
    private long expiration = 86400000; // 24 horas em milissegundos
}

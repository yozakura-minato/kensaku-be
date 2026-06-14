package com.yozakura_minato.kensaku_be.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfiguration {

    @Value("${security.fe.url}")
    private String feUrl;

    /**
     * <p>CORS information:</p>
     * <ul>
     *     <li>Allowed origins: {@code security.fe.url}</li>
     *     <li>Allowed methods: all</li>
     *     <li>Allowed headers: all</li>
     *     <li>Allow credentials</li>
     *     <li>Max age: 1 hour</li>
     * </ul>
     * @return (CorsConfigurationSource)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(feUrl));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}

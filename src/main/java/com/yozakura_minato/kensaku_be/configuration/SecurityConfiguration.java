package com.yozakura_minato.kensaku_be.configuration;

import com.yozakura_minato.kensaku_be.service.implement.UserDetailServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
class SecurityConfiguration {

    @Value("${security.jwt.decode.secret-key}")
    private String key;

    @Value("${security.be.public-paths}")
    private String[] bePublicPaths;

    @Autowired
    private UserDetailServiceImplement userDetailServiceImplement;

    @Autowired
    private JwtDecoderConfiguration jwtDecoderConfiguration;

    /**
     * <p>Filter chain information:</p>
     * <ul>
     *     <li>CSRF: disable</li>
     *     <li>Authentication required: all paths, except {@code security.be.public-paths}</li>
     *     <li>JWT decoder: using {@code JwtDecoderConfiguration}</li>
     * </ul>
     * @param httpSecurity (HttpSecurity)
     * @return (O)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers(bePublicPaths).permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                        (oAuth2ResourceServer) -> oAuth2ResourceServer
                                .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoderConfiguration))
                );
        return httpSecurity.build();
    }

    /**
     * <p>Using:</p>
     * <ul>
     *     <li>Secret key: {@code security.jwt.decode.secret-key}</li>
     *     <li>Algorithm: {@code HS256} </li>
     * </ul>
     * @return (NimbusJwtDecoder)
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HS256");
        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    /**
     * <p>Using:</p>
     * <ul>
     *     <li>Authentication provider: {@code UserDetailServiceImplement}</li>
     *     <li>Password encoder: {@code BCryptPasswordEncoder}</li>
     * </ul>
     * @return (ProviderManager)
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userDetailServiceImplement);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    /**
     * @return (BCryptPasswordEncoder)
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

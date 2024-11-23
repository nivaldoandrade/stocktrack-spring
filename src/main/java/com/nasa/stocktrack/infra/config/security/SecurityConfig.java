package com.nasa.stocktrack.infra.config.security;


import com.nasa.stocktrack.application.gateways.AuthProviderGateway;
import com.nasa.stocktrack.application.gateways.TokenGateway;
import com.nasa.stocktrack.application.services.AuthService;
import com.nasa.stocktrack.infra.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                "/auth/signin",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    AuthService authService(AuthProviderGateway authProviderGateway, TokenGateway tokenGateway) {
        return new AuthService(authProviderGateway, tokenGateway);
    }
}

package com.example.shoppingweb.config.security;

import com.example.shoppingweb.service.impl.UserAdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityAdminConfig {
    private final UserAdminService userAdminService;

    public SecurityAdminConfig(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.disable())
                .csrf(c -> c.disable())
                .authorizeHttpRequests(requests -> requests
//                                .requestMatchers("/admin/**").permitAll()
//                        .requestMatchers("/detail").hasAnyAuthority("STAFF","ADMIN")
//                        .requestMatchers("/add").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userAdminService)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}

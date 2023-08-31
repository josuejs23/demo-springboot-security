package com.josuejs23.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails josue =  User.builder()
                .username("josue")
                .password("{noop}test123")
                .roles("employee")
                .build();

        UserDetails lili = User.builder()
                .username("lili")
                .password("{noop}test123")
                .roles("employee","manager")
                .build();

        UserDetails juan = User.builder()
                .username("juan")
                .password("{noop}test123")
                .roles("employee","manager","admin")
                .build();

        return new InMemoryUserDetailsManager(josue, lili, juan);
    }
}

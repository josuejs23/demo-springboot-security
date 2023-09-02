package com.josuejs23.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails josue =  User.builder()
                .username("josue")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails lili = User.builder()
                .username("lili")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails juan = User.builder()
                .username("juan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(josue, lili, juan);
    }*/

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    // This is to add a custom form login
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests( configurer ->
            configurer
                    .requestMatchers("/").hasRole("EMPLOYEE")
                    .requestMatchers("/leaders/**").hasRole("MANAGER")
                    .requestMatchers("/system/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
        )
        .formLogin(form->
                form
                        .loginPage("/showLoginPage")
                        //This url comes free by Spring boot
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        )
        .logout( logout -> logout.permitAll())
        .exceptionHandling( configurer -> configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}

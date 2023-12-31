package com.example.registrationloginsystem.configuration;

import com.example.registrationloginsystem.security.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

//    private CustomUserDetailsService customUserDetailsService;

    //PasswordEncoder is an interface for encoding passwords
    //BCryptPasswordEncoder is an implementation of PasswordEncoder that uses the BCrypt strong hashing function
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Configure SecurityFilterChain to use the provided SecurityBuilder
    //SecurityFilterChain is a filter chain that is managed by Spring Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/add/**").permitAll();
                    authorize.requestMatchers("/index").permitAll();
                    authorize.requestMatchers("/users").hasRole("ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                ).logout(
                        logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );
        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
}

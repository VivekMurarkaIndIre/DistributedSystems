package com.vivek.practice.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    // Injecting JWT custom authentication provider
    @Autowired
    JwtTokenProvider customJWTAuthenticationProvider;

    // Injecting Google custom authentication provider
//    @Autowired
//    GoogleCloudAuthenticationProvider googleCloudAuthenticationProvider;

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // adding our custom authentication providers
    // authentication manager will call these custom provider's
    // authenticate methods from now on.
//    @Autowired
//    void registerProvider(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(customAuthenticationProvider)
//                .authenticationProvider(googleCloudAuthenticationProvider);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disabling csrf since we won't use form login
                .csrf().disable()
                // setting stateless session, because we choose to implement Rest API
                // No session will be created or used by spring security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                // giving permission to every request for /login endpoint
                http.authorizeHttpRequests().requestMatchers("/users/signin").permitAll()//
                        .requestMatchers("/users/signup").permitAll()//
                        .requestMatchers("/h2-console/**/**").permitAll()
                // for everything else, the user has to be authenticated
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/users/signup");

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(customJWTAuthenticationProvider));



        return http.build();
    }

//    public void configure(WebSecurity web) throws Exception {
//        // Allow swagger to be accessed without authentication
//        web.ignoring().requestMatchers("/learning/dev")
//
//
//                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
//                .and()
//                .ignoring()
//                .requestMatchers("/h2-console/**/**");;
//    }

    //used by uer service
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
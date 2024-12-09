package com.zpi.fryzland.security;

import com.zpi.fryzland.security.authentication.CustomAuthenticationProvider;
import com.zpi.fryzland.security.jwt.JwtAuthenticationEntryPoint;
import com.zpi.fryzland.security.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests((authorize) -> authorize

                        // Auth endpoints
                        .requestMatchers("/api/auth/employee/register").hasRole("USER_ADMIN")
                        .requestMatchers("/api/auth/**").permitAll()

                        // Salon endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/salons").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/salons/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/salons/{salonID}").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/salons").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/crud/salons").hasRole("USER_ADMIN")

                        // Assignment to salon
                        .requestMatchers(HttpMethod.GET, "/api/crud/assignment-to-salon").hasAnyRole("USER_EMPLOYEE", "USER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/assignment-to-salon/{assignmentID}").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/assignment-to-salon").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/crud/assignment-to-salon").hasRole("USER_ADMIN")

                        // Appointment making
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/availability-dates/{salonId}/{employeeId}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/cancel-customer/{visitId}").hasRole("USER_CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/cancel-employee/{visitId}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/opening-hours/{salonId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/services-and-categories/{salonId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/time-slots/{employeeId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/crud/appointment-making/save-visit").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/reschedule-visit/{visitId}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")

                        // Visit endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE", "USER_ADMIN")

                        // Service endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/service/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/service/{serviceID}").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/service").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/crud/service").hasRole("USER_ADMIN")

                        // Rating endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForEmployee/{employeeID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForSalon/{salonID}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForSalonWithCustomer/{salonID}").hasRole("USER_CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/crud/rating").hasRole("USER_CUSTOMER")

                        // Email test endpoints
                        .requestMatchers(HttpMethod.POST, "/api/test-email/**").hasRole("USER_ADMIN")

                        // Employee qualification endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/employee-qualification").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/employee-qualification/{id}").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/employee-qualification").hasRole("USER_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/crud/employee-qualification").hasRole("USER_ADMIN")

                        // Miscellaneous
                        .requestMatchers("**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","PUT","DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

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
                        .requestMatchers("/api/auth/employee/register").hasRole("USER_EMPLOYEE")
                        .requestMatchers("/api/auth/**").permitAll()

                        // Salon endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/salons").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/salons/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/salons/{salonID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/salons").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/salons").hasRole("USER_EMPLOYEE")

                        // Assignment to salon
                        .requestMatchers(HttpMethod.GET, "/api/crud/assignment-to-salon").hasAnyRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/assignment-to-salon/{assignmentID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/assignment-to-salon").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/assignment-to-salon").hasRole("USER_EMPLOYEE")

                        // Appointment making
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/availability-dates/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/cancel-customer/{visitId}").hasRole("USER_CUSTOMER")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/cancel-employee/{visitId}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/opening-hours/{salonId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/services-and-categories/{salonId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/appointment-making/time-slots/{employeeId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/crud/appointment-making/save-visit").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/appointment-making/reschedule-visit/{visitId}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")

                        // Visit endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/visit/**").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")

                        // Service endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/service/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/service/{serviceID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/service").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/service").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/service/getAllById").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")

                        // Rating endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForEmployee/{employeeID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForSalon/{salonID}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/rating/allForSalonWithCustomer/{salonID}").hasRole("USER_CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/crud/rating").hasRole("USER_CUSTOMER")

                        // Email test endpoints
                        .requestMatchers(HttpMethod.POST, "/api/test-email/**").hasRole("USER_EMPLOYEE")

                        // Employee qualification endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/employee-qualification").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/employee-qualification/{id}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/employee-qualification").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/employee-qualification").hasRole("USER_EMPLOYEE")

                        // Service Category endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/service-category/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/crud/service-category").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/service-category").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/service-category/{id}").hasRole("USER_EMPLOYEE")

                        // Customer endpoints
                        .requestMatchers(HttpMethod.POST, "/api/crud/customer/find-by-email").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/customer/getAllById").hasAnyRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/customer/{customerID}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/customer").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/customer").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/customer/{customerID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/customer/password-change-request").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/customer/password-change/{requestID}").permitAll()


                        // Employee endpoints
                        .requestMatchers(HttpMethod.POST, "/api/crud/employee/find-by-email").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/employee/getAllByIds").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/employee/{employeeID}").hasAnyRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/crud/employee").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/employee").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/employee/{employeeID}").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/employee/password-change-request").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/crud/employee/password-change/{requestID}").permitAll()

                        // Opening Hours endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/opening-hours/{openingHoursID}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/crud/opening-hours").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT, "/api/crud/opening-hours").hasRole("USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.DELETE, "/api/crud/opening-hours/{openingHoursID}").hasRole("USER_EMPLOYEE")

                        // Service Visit endpoints
                        .requestMatchers(HttpMethod.GET, "/api/crud/service-visit/forCustomer/{id}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/service-visit/forEmployee/{id}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/crud/service-visit/forSalon/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/crud/service-visit/forVisit/{id}").hasAnyRole("USER_CUSTOMER", "USER_EMPLOYEE")

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

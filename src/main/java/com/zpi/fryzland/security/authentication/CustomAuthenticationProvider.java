package com.zpi.fryzland.security.authentication;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    @Autowired
    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        super();
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
        this.userDetailsService=userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        GrantedAuthority role = authentication.getAuthorities().iterator().next();
        UserDetails userDetails = userDetailsService.loadUserByUsernameWithRole(email, role.getAuthority());
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with email: " + email + " and role: " + role);
        }
        if (userDetails.getUsername().equals(email) && getPasswordEncoder().matches(password, userDetails.getPassword())){
            System.out.println("It went through");
            return authentication;
        }
        else{
            throw new BadCredentialsException("User has bad credentials");
        }
    }
}

package com.zpi.fryzland.security.authentication;

import com.zpi.fryzland.dto.LoginDTO;
import com.zpi.fryzland.security.jwt.JwtTokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;
    private JwtTokenGenerator jwtTokenGenerator;
    public String login(LoginDTO loginDTO, String role){
        UserDetails userDetails = userDetailsService.loadUserByUsernameWithRole(loginDTO.getEmail(), role);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword(),
                userDetails.getAuthorities());

        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(
                authentication
                );
        return jwtTokenGenerator.generateToken(authentication);
    }
}

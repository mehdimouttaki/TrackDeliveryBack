package com.trackdelivery.serviceImpl;

import com.trackdelivery.config.JwtService;
import com.trackdelivery.dto.request.AuthRequest;
import com.trackdelivery.dto.response.AuthResponse;
import com.trackdelivery.entity.Client;
import com.trackdelivery.enums.Role;
import com.trackdelivery.repository.ClientRepository;
import com.trackdelivery.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }




    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Client client = clientRepository.findByEmail(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(client);
        return AuthResponse.builder().token(jwtToken).build();
    }
}

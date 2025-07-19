package com.trackdelivery.controller;

import com.trackdelivery.config.JwtService;
import com.trackdelivery.dto.request.AuthRequest;
import com.trackdelivery.dto.response.AuthResponse;
import com.trackdelivery.entity.Client;
import com.trackdelivery.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ClientRepository clientRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Client client = clientRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(client);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}

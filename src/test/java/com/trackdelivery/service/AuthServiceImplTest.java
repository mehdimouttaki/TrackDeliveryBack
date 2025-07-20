package com.trackdelivery.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.trackdelivery.config.JwtService;
import com.trackdelivery.dto.request.AuthRequest;
import com.trackdelivery.dto.response.AuthResponse;
import com.trackdelivery.entity.Client;
import com.trackdelivery.repository.ClientRepository;
import com.trackdelivery.serviceImpl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;



    @Test
    void authenticate_shouldReturnToken_whenCredentialsAreValid() {
        // Prepare test data
        AuthRequest request = new AuthRequest();
        request.setUsername("test@example.com");
        request.setPassword("password123");

        Client client = new Client();
        client.setEmail("test@example.com");
        // set other properties if needed

        String fakeJwtToken = "fake-jwt-token";

        // Mock behaviors
        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);

        when(clientRepository.findByEmail("test@example.com")).thenReturn(Optional.of(client));
        when(jwtService.generateToken(client)).thenReturn(fakeJwtToken);

        // Call the method to test
        AuthResponse response = authService.authenticate(request);

        // Verify
        assertNotNull(response);
        assertEquals(fakeJwtToken, response.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(clientRepository).findByEmail("test@example.com");
        verify(jwtService).generateToken(client);
    }


    @Test
    void authenticate_shouldThrowException_whenUserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setUsername("notfound@example.com");
        request.setPassword("password");

        // Mock authenticate to return a mock Authentication object
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));
        // Mock clientRepository to return empty to simulate user not found
        when(clientRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        // Assert exception thrown (NoSuchElementException from orElseThrow)
        assertThrows(java.util.NoSuchElementException.class, () -> {
            authService.authenticate(request);
        });
    }

}

package com.trackdelivery.controller;

import com.trackdelivery.config.JwtService;
import com.trackdelivery.dto.request.AuthRequest;
import com.trackdelivery.dto.response.AuthResponse;
import com.trackdelivery.entity.Client;
import com.trackdelivery.entity.Orders;
import com.trackdelivery.enums.Role;
import com.trackdelivery.enums.State;
import com.trackdelivery.repository.ClientRepository;
import com.trackdelivery.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/apis")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Client client = clientRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(client);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

    @PostConstruct
    public void init(){
        boolean existed= clientRepository.existsByUsername("mehdimouttaki99@gmail.com");
        if (!existed){
            Client client =new Client();
            client.setFirstName("Mehdi");
            client.setLastName("Mouttaki");
            client.setUsername("mehdimouttaki99@gmail.com");
            client.setPassword(passwordEncoder.encode("123456"));
            client.setPhoneNumber("0603060804");
            client.setAddress("Casablanca");
            client.setCin("BK681416");
            client.setActive(Boolean.TRUE);
            client.setRole(Role.CLIENT);
            clientRepository.save(client);



            Orders order1 = new Orders();
            order1.setDescription("Order 1");
            order1.setSource("Casablanca");
            order1.setAddress("Maarif");
            order1.setDateOrder(LocalDateTime.now());
            order1.setState(State.READY);
            order1.setOrderNumber("ORD-1001");
            order1.setOrderNumberTemporary("TMP-1001");
            order1.setClient(client);
            order1.setCanceled(false);
            order1.setActive(true);
            orderRepository.save(order1);


            Orders order2 = new Orders();
            order2.setDescription("Order 2");
            order2.setSource("Rabat");
            order2.setAddress("Hay Riad");
            order2.setDateOrder(LocalDateTime.now());
            order2.setState(State.ACCEPTED);
            order2.setOrderNumber("ORD-1002");
            order2.setOrderNumberTemporary("TMP-1002");
            order2.setClient(client);
            order2.setCanceled(false);
            order2.setActive(true);
            orderRepository.save(order2);
        }
}}

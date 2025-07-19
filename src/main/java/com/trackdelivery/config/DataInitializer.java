package com.trackdelivery.config;

import com.trackdelivery.entity.Client;
import com.trackdelivery.entity.Orders;
import com.trackdelivery.enums.Role;
import com.trackdelivery.enums.State;
import com.trackdelivery.repository.ClientRepository;
import com.trackdelivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (clientRepository.count() == 0) {

                Client client1 = new Client();
                client1.setFirstName("EL MEHDI");
                client1.setLastName("El Mouttaki");
                client1.setEmail("mehdimouttaki99@gmail.com");
                client1.setPhoneNumber("0603060804");
                client1.setAddress("Casablanca, Sidi Maarouf");
                client1.setCin("BK681416");
                client1.setPassword(passwordEncoder.encode("password"));
                client1.setRole(Role.CLIENT);
                client1.setActive(true);

                Client client2 = new Client();
                client2.setFirstName("Youssef");
                client2.setLastName("Najeh");
                client2.setEmail("youssefnajeh@gmail.com");
                client2.setPhoneNumber("0650122369");
                client2.setAddress("Casablanca, Bouskoura");
                client2.setCin("MC1412596");
                client2.setPassword(passwordEncoder.encode("123456"));
                client2.setRole(Role.CLIENT);
                client2.setActive(true);

                Client client3 = new Client();
                client3.setFirstName("Rida");
                client3.setLastName("Hsika");
                client3.setEmail("ridahsika@gmail.com");
                client3.setPhoneNumber("0603058963");
                client3.setAddress("Casablanca, Lisasfa");
                client3.setCin("BK691456");
                client3.setPassword(passwordEncoder.encode("123456"));
                client3.setRole(Role.CLIENT);
                client3.setActive(true);

                clientRepository.save(client1);
                clientRepository.save(client2);
                clientRepository.save(client3);

// Orders

                Orders orders1 = new Orders();
                orders1.setDescription("Order 1");
                orders1.setSource("Casablanca");
                orders1.setAddress("Maarif");
                orders1.setDateOrder(LocalDateTime.now());
                orders1.setState(State.READY);
                orders1.setOrderNumber("ORD-1001");
                orders1.setOrderNumberTemporary("TMP-1001");
                orders1.setClient(client1);
                orders1.setCanceled(false);
                orders1.setActive(true);

                Orders orders2 = new Orders();
                orders2.setDescription("Order 2");
                orders2.setSource("Rabat");
                orders2.setAddress("Hay Riad");
                orders2.setDateOrder(LocalDateTime.now());
                orders2.setState(State.READY);
                orders2.setOrderNumber("ORD-1002");
                orders2.setOrderNumberTemporary("TMP-1002");
                orders2.setClient(client2);
                orders2.setCanceled(false);
                orders2.setActive(true);

                Orders orders3 = new Orders();
                orders3.setDescription("Order 3");
                orders3.setSource("Ourzazat");
                orders3.setAddress("Fes");
                orders3.setDateOrder(LocalDateTime.now());
                orders3.setState(State.DELAYED);
                orders3.setOrderNumber("ORD-1003");
                orders3.setOrderNumberTemporary("TMP-1003");
                orders3.setClient(client2);
                orders3.setCanceled(false);
                orders3.setActive(true);

                Orders orders4 = new Orders();
                orders4.setDescription("Order 4");
                orders4.setSource("Tanger");
                orders4.setAddress("Rabat");
                orders4.setDateOrder(LocalDateTime.now());
                orders4.setState(State.DELIVERING);
                orders4.setOrderNumber("ORD-1004");
                orders4.setOrderNumberTemporary("TMP-1004");
                orders4.setClient(client3);
                orders4.setCanceled(false);
                orders4.setActive(true);

                Orders orders5 = new Orders();
                orders5.setDescription("Order 5");
                orders5.setSource("Tetouan");
                orders5.setAddress("Martile");
                orders5.setDateOrder(LocalDateTime.now());
                orders5.setState(State.DELAYED);
                orders5.setOrderNumber("ORD-1005");
                orders5.setOrderNumberTemporary("TMP-1005");
                orders5.setClient(client1);
                orders5.setCanceled(false);
                orders5.setActive(true);

                Orders orders6 = new Orders();
                orders6.setDescription("Order 6");
                orders6.setSource("Casablanca");
                orders6.setAddress("Fes");
                orders6.setDateOrder(LocalDateTime.now());
                orders6.setState(State.DELIVERING);
                orders6.setOrderNumber("ORD-1006");
                orders6.setOrderNumberTemporary("TMP-1006");
                orders6.setClient(client1);
                orders6.setCanceled(false);
                orders6.setActive(true);

                orderRepository.save(orders1);
                orderRepository.save(orders2);
                orderRepository.save(orders3);
                orderRepository.save(orders4);
                orderRepository.save(orders5);
                orderRepository.save(orders6);


                System.out.println("✅ Clients and Orders initialized.");
            }
        };
    }
}

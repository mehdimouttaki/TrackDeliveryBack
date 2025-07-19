-- Insert clients
INSERT INTO clients (first_name, last_name, email, phone_number, address, cin, password, role, active) VALUES
                                                                                                          ('EL MEHDI', 'El Mouttaki', 'mehdimouttaki99@gmail.com', '0603060804', 'Casablanca, Sidi Maarouf', 'BK681416', '$2a$10$Dow1pJ9ck9Q4yTpSOK3.KO3Vh/D6QaYrz3Vf8J2qTizdy7Shsmk5G', 'CLIENT', TRUE),
                                                                                                          ('Youssef', 'Najeh', 'youssefnajeh@gmail.com', '0650122369', 'Casablanca, Bouskoura', 'MC1412596', '$2a$10$E6g7WbFGvYH4zx0YH2vJMeDlRUIZkBA2JhA1B0VpXXeM6SzCK4Q9e', 'CLIENT', TRUE),
                                                                                                          ('Rida', 'Hsika', 'ridahsika@gmail.com', '0603058963', 'Casablanca, Lisasfa', 'BK691456', '$2a$10$E6g7WbFGvYH4zx0YH2vJMeDlRUIZkBA2JhA1B0VpXXeM6SzCK4Q9e', 'CLIENT', TRUE);


-- Note: Replace '$2a$10$YOUR_ENCODED_PASSWORDX' with actual BCrypt hashed passwords

-- Insert orders
INSERT INTO orders (description, source, address, date_order, state, order_number, order_number_temporary, client_id, canceled, active) VALUES
                                                                                                                                            ('Order 1', 'Casablanca', 'Maarif', NOW(), 'READY', 'ORD-1001', 'TMP-1001', (SELECT id FROM clients WHERE email = 'mehdimouttaki99@gmail.com'), FALSE, TRUE),
                                                                                                                                            ('Order 2', 'Rabat', 'Hay Riad', NOW(), 'READY', 'ORD-1002', 'TMP-1002', (SELECT id FROM clients WHERE email = 'youssefnajeh@gmail.com'), FALSE, TRUE),
                                                                                                                                            ('Order 3', 'Ourzazat', 'Fes', NOW(), 'DELAYED', 'ORD-1003', 'TMP-1003', (SELECT id FROM clients WHERE email = 'youssefnajeh@gmail.com'), FALSE, TRUE),
                                                                                                                                            ('Order 4', 'Tanger', 'Rabat', NOW(), 'DELIVERING', 'ORD-1004', 'TMP-1004', (SELECT id FROM clients WHERE email = 'ridahsika@gmail.com'), FALSE, TRUE),
                                                                                                                                            ('Order 5', 'Tetouan', 'Martile', NOW(), 'DELAYED', 'ORD-1005', 'TMP-1005', (SELECT id FROM clients WHERE email = 'mehdimouttaki99@gmail.com'), FALSE, TRUE),
                                                                                                                                            ('Order 6', 'Casablanca', 'Fes', NOW(), 'DELIVERING', 'ORD-1006', 'TMP-1006', (SELECT id FROM clients WHERE email = 'mehdimouttaki99@gmail.com'), FALSE, TRUE);

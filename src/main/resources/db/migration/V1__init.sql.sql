DROP TYPE IF EXISTS role;
DROP TYPE IF EXISTS state;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS clients;

CREATE TYPE role AS ENUM ('CLIENT');
CREATE TYPE state AS ENUM ('READY', 'DELIVERING', 'DELAYED');

CREATE TABLE clients (
                         id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(255) NOT NULL,
                         last_name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         phone_number VARCHAR(20),
                         address VARCHAR(255),
                         cin VARCHAR(50) UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role role NOT NULL,
                         active BOOLEAN DEFAULT TRUE
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        description TEXT,
                        source VARCHAR(255),
                        address VARCHAR(255),
                        date_order TIMESTAMP,
                        state state NOT NULL,
                        order_number VARCHAR(255) UNIQUE,
                        order_number_temporary VARCHAR(255),
                        client_id BIGINT REFERENCES clients(id) ON DELETE SET NULL,
                        canceled BOOLEAN DEFAULT FALSE,
                        active BOOLEAN DEFAULT TRUE
);


# 🚚 TrackDelivery Backend

**TrackDeliveryBack** is a Spring Boot 3 REST API project for managing delivery orders and client authentication using JWT. It provides secure endpoints for client login, order filtering, creation, and updates — designed to work seamlessly with an Angular frontend.

---

## 📌 Features

- ✅ **JWT Authentication** with Spring Security
- ✅ **Role-based Authorization** (Client Role)
- ✅ **Order Management** (CRUD operations)
- ✅ **Client Management**
- ✅ **Order Filtering & Searching**
- ✅ **CORS Configuration** for Angular Frontend Integration
- ✅ **DTO Mapping with MapStruct**
- ✅ **Database Initialization** with sample data (Clients & Orders)

---

## 🏗️ Tech Stack

| Technology       | Purpose                              |
|------------------|---------------------------------------|
| **Spring Boot 3**    | Backend Framework               |
| **Spring Security**  | Authentication & Authorization (JWT) |
| **Spring Data JPA**  | ORM & Database Access           |
| **MapStruct**        | DTO ↔ Entity Mapping            |
| **PostgreSQL**       | Relational Database              |
| **Lombok**           | Boilerplate Code Reduction       |
| **Maven**            | Dependency Management            |

---

## 📁 Project Structure

```
src/main/java/com/trackdelivery/
│
├── config/           # Security, JWT, and CORS Configuration
├── controller/       # REST API Controllers
├── dto/              # Request and Response DTOs
├── entity/           # JPA Entities (Client, Orders)
├── enums/            # Enum Types (Role, State)
├── mapper/           # MapStruct Mappers
├── repository/       # Spring Data Repositories
└── service/          # Business Logic Services
```

---

## 📡 API Endpoints

### Authentication
- **POST** `/api/auth/authenticate` → Login and receive JWT token.

✅ **Request:**
```json
{
  "username": "email@example.com",
  "password": "password"
}
```

✅ **Response:**
```json
{
  "token": "your_jwt_token"
}
```
Then use the token in all secured requests:
```
Authorization: Bearer {token}
```

### Orders
- **GET** `/order/filter/table/all` → Filter and search orders.
- **POST** `/order` → Create a new order.
- **PUT** `/order/{id}` → Update existing order.
- **DELETE** `/order/{id}` → Soft delete an order.

---

## 🚀 Getting Started

### ✅ Prerequisites
- Java 21
- Maven 3.8+
- PostgreSQL installed and running

### ⚙️ Setup

1. **Clone the repository:**
```bash
git clone https://github.com/mehdimouttaki/TrackDeliveryBack.git
cd TrackDeliveryBack
```

2. **Configure your PostgreSQL Database:**

Create a database:
```sql
CREATE DATABASE track_delivery;
```

Update `application.properties` with your PostgreSQL username and password.

3. **Run the application:**
```bash
./mvnw spring-boot:run
```
or with Maven:
```bash
mvn spring-boot:run
```

---

## 📝 Notes

- On first run, the database will be auto-initialized with **clients** and **orders**.
- CORS is configured to allow requests from `http://localhost:4200` (Angular frontend).

---

## 🧑‍💻 Author

**EL MEHDI EL MOUTTAKI**

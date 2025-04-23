# 🅿️ BackendParkingApp

A robust and scalable **Spring Boot backend** for a smart parking management system. This API powers user authentication, parking space administration, real-time booking, and reporting features for both end users and administrators.

## 🚗 Project Overview

The system ensures a seamless experience in managing parking spaces, enabling registered users to:
- Book and manage parking spots
- View parking history
- Manage vehicles

While administrators benefit from:
- Dashboard access to manage users and parking areas
- Reporting and analytics capabilities
- Secure authentication and user roles

---

## 🧰 Technologies Used

- **Java 11+**
- **Spring Boot**
- **Spring MVC & Spring Security**
- **Spring Data JPA**
- **JWT Authentication**
- **MySQL**
- **RESTful API**
- **Maven**

---

## 📁 Folder Structure

```
src/main/java/tn/esprit/spring/
├── controllers/           # REST endpoints
│   ├── HistoryController.java
│   ├── ParkingController.java
│   ├── PlaceParkingController.java
│   ├── UserController.java
│   └── VoitureController.java
├── entities/              # Domain models (User, Voiture, Parking, etc.)
├── repositories/          # JPA interfaces for data access
├── requests/              # Incoming DTOs
├── responses/             # API responses
├── security/              # JWT config & user authentication
├── services/              # Business logic services
├── shared/                # Utilities/shared logic
├── Application.java       # Main Spring Boot entry
├── WebConfig.java         # CORS & app-level config
└── SpringApplicationContext.java
```

---

## ⚙️ Getting Started

### Prerequisites

- Java 11+
- Maven
- MySQL server
- Postman or similar API testing tool

### Setup Steps

1. **Clone the repository**

```bash
git clone https://github.com/MezhoudMedIsmail/BackendParkingApp.git
cd BackendParkingApp
```

2. **Create MySQL database**

Create a database named `parking_app_db` and configure your credentials in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parking_app_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Run the app**

```bash
mvn spring-boot:run
```

App should launch at `http://localhost:8080`

---

## 🔐 Authentication

- Uses **JWT tokens** for secure access
- Token must be added to each protected request:

```
Authorization: Bearer <your_token>
```

---

## 📬 Key Endpoints

| Controller              | Description                         |
|-------------------------|-------------------------------------|
| `/api/users`            | User registration & management      |
| `/api/parking`          | Create/manage parking lots          |
| `/api/places`           | Manage individual parking spots     |
| `/api/voitures`         | Register and manage vehicles        |
| `/api/history`          | User parking history & logs         |

---

## 🚀 Features

- Role-based access for admin & users
- Parking spot booking & history tracking
- Dashboard-ready backend for admins
- Modular, secure architecture

---

## 📌 Future Roadmap

- Add billing & payment module
- Email/SMS notification system
- Admin dashboard charts

---

## 🤝 Contributing

Contributions are welcome! Fork the repo and submit a pull request. For large changes, open an issue first.

---

Built with ❤️ using Spring Boot by [MezhoudMedIsmail](https://github.com/MezhoudMedIsmail)

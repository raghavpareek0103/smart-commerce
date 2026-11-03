# 🛒 Smart Commerce — Full Stack E-Commerce Platform

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-6DB33F?logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-TypeScript-3178C6?logo=react&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/Security-JWT-informational)
![Status](https://img.shields.io/badge/Status-Development-yellow)

> A full-stack multi-vendor e-commerce platform built using **Java Spring Boot** and **React + TypeScript**, providing dedicated functionality for customers, sellers, and administrators.

The platform includes authentication and authorization, product management, shopping cart, orders, payments, reviews, seller management, and administrative features.

---

## 👨‍💻 Project Information

| Category | Details |
|---|---|
| **Developer** | Raghav Pareek |
| **Project** | Smart Commerce |
| **Type** | Full Stack Web Application |
| **Architecture** | REST API + React Frontend |
| **Backend** | Java Spring Boot |
| **Frontend** | React + TypeScript |
| **Database** | MySQL |

---

## 📁 Project Structure

```text
Smart-Commerce/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── Dockerfile
│   ├── mvnw
│   └── mvnw.cmd
│
├── frontend/
│   ├── src/
│   ├── public/
│   ├── package.json
│   └── ...
│
├── assets/
│   └── images/
│       ├── homepage.png
│       └── login.png
│
└── README.md
```

---

## 🧰 Development Tools

- IntelliJ IDEA
- Visual Studio Code
- Java 17
- Node.js
- npm
- MySQL
- Git & GitHub
- Maven

---

## 🧱 Technology Stack

### Backend

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- REST APIs
- Java Mail Sender
- Lombok
- Maven

### Frontend

- React
- TypeScript
- Redux Toolkit
- React Router DOM
- Axios
- Tailwind CSS
- Material UI
- React Charts
- Formik
- Yup

### Database

- MySQL

### Payment Integration

- Razorpay
- Stripe

---

## ✨ Main Features

### 👤 Customer Module

- User registration and login
- JWT-based authentication
- Product browsing
- Product search
- Product filtering
- Product sorting
- Pagination
- Shopping cart
- Add, update, and remove cart items
- Wishlist
- Product reviews and ratings
- Address management
- Coupon support
- Order placement
- Order history
- Order cancellation
- Account management
- Online payment integration
- Customer support/chatbot functionality

### 🛍️ Seller Module

- Seller registration
- Seller authentication
- Seller dashboard
- Product management
- Add products
- Update products
- Delete products
- Product inventory management
- Order management
- Sales reports
- Earnings reports
- Refund information
- Cancellation information
- Payment and transaction history
- Seller profile management
- Sales analytics

### 🛠️ Admin Module

- Admin authentication
- Admin dashboard
- Seller management
- Seller approval
- Seller suspension
- Product and category management
- Coupon management
- Deal and promotion management
- Homepage management
- Platform administration

---

## 🔐 Authentication & Security

The backend uses **Spring Security** and **JWT** for authentication and authorization.

### Security Features

- User authentication
- JWT token generation
- JWT token validation
- Role-based authorization
- Protected REST APIs
- Password encryption
- Request validation
- CORS configuration

### Supported Roles

```text
CUSTOMER
SELLER
ADMIN
```

---

## 🗂️ Backend Architecture

The backend follows a layered architecture:

```text
┌─────────────────────┐
│     Controller      │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│      Service        │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│     Repository      │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│      MySQL DB       │
└─────────────────────┘
```

### Additional Layers

- DTO
- Entity / Model
- Mapper
- Exception Handling
- Security
- Configuration
- Utilities

### Backend Package Structure

```text
com.raghav.ecommerce
│
├── ai
├── config
├── controller
├── domain
├── dto
├── exception
├── mapper
├── model
├── repository
├── request
├── response
├── service
├── utils
│
└── EcommerceApplication.java
```

---

## 🗃️ Core Entities

The application contains several core business entities:

- User
- Seller
- Product
- Category
- Cart
- CartItem
- Order
- OrderItem
- Address
- Payment
- Transaction
- Coupon
- Wishlist
- Review
- Verification Code
- Seller Report

---

## 🔄 Application Flow

The high-level application flow is:

```text
┌─────────────────────┐
│   React Frontend    │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│    Axios Request    │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│ Spring Boot REST API│
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│     Controller      │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│   Service Layer     │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│  Repository Layer   │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│    MySQL Database   │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│    JSON Response    │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│ React / Redux State │
└──────────┬──────────┘
           ↓
┌─────────────────────┐
│    User Interface   │
└─────────────────────┘
```

---

## 💳 Payment Integration

The platform supports online payment processing through:

### Razorpay

Used for payment processing for supported customers.

### Stripe

Used for international payment processing.

> **Security Note:** Payment credentials must always be stored using environment variables or secure configuration and should never be committed to GitHub.

---

## 🗄️ Database Configuration

The backend uses **MySQL**.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_commerce?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Never commit real database passwords, payment credentials, JWT secrets, or email credentials to GitHub.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd Smart-Commerce
```

---

### ⚙️ Backend Setup

Navigate to the backend directory:

```bash
cd backend
```

Make sure the following are installed:

- Java 17
- MySQL
- Maven

Create/configure:

```text
backend/src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_commerce?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwt.secret=YOUR_JWT_SECRET
app.jwt.expiration-ms=86400000
```

### Run the Backend

#### Windows

```bash
mvnw.cmd spring-boot:run
```

#### Linux/macOS

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

---

### 🌐 Frontend Setup

Open a new terminal and navigate to the frontend:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will normally be available at:

```text
http://localhost:5173
```

---

## 🔑 Environment Variables

Do not commit private credentials to the repository.

### Frontend `.env`

```env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_RAZORPAY_KEY_ID=YOUR_RAZORPAY_PUBLIC_KEY
VITE_STRIPE_PK=YOUR_STRIPE_PUBLISHABLE_KEY
```

### Backend Environment Configuration

```env
DB_USERNAME=YOUR_DB_USER
DB_PASSWORD=YOUR_DB_PASSWORD
JWT_SECRET=YOUR_JWT_SECRET
RAZORPAY_KEY_ID=YOUR_RAZORPAY_KEY
RAZORPAY_KEY_SECRET=YOUR_RAZORPAY_SECRET
STRIPE_SECRET_KEY=YOUR_STRIPE_SECRET
```

---

## 🧪 Testing

### Run Backend Tests

```bash
mvn test
```

### Build Backend

```bash
mvn clean package
```

### Build Frontend

```bash
npm run build
```

---

## 📊 Project Status

### Current Implementation

- ✅ Spring Boot backend
- ✅ MySQL database integration
- ✅ Spring Data JPA
- ✅ Spring Security
- ✅ JWT authentication
- ✅ REST APIs
- ✅ Customer functionality
- ✅ Seller functionality
- ✅ Admin functionality
- ✅ Product management
- ✅ Cart management
- ✅ Order management
- ✅ Payment integration
- ✅ React frontend
- ✅ Redux state management
- ✅ API integration

---

## 📸 Screenshots

### Homepage

_Add homepage screenshot here._

### Login

_Add login screenshot here._

---

## 🛡️ Security Practices

The application follows common security practices including:

- JWT-based authentication
- Password encryption
- Role-based access control
- Input validation
- CORS configuration
- Protected APIs
- Environment-based secrets
- Secure payment configuration

For production deployments, the application should use **HTTPS** and secure secret management.

---

## 📈 Future Improvements

Planned improvements include:

- Redis caching
- Advanced product search
- Elasticsearch integration
- Dockerized deployment
- CI/CD pipeline
- Cloud deployment
- Improved analytics
- Performance optimization
- Centralized logging
- Monitoring and observability
- Automated integration testing

---

## 🤝 Development

Smart Commerce is maintained and customized as a full-stack e-commerce application.

Development focuses on:

- Backend API development
- Frontend integration
- Authentication and authorization
- Database design
- Payment integration
- E-commerce business logic
- Performance improvements
- Security improvements

---

## 📄 License

This project includes components adapted from an existing e-commerce implementation.

Please refer to the repository's original license and attribution requirements before redistributing or presenting the project as entirely original work.
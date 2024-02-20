# Microservices Development with Banking Application Focus

## Overview
This project focuses on developing a comprehensive Microservices architecture for a banking application. It includes Customer Management and Account Management services to facilitate efficient banking operations. Key features of the project include efficient service discovery, load balancing, enhanced security measures, fault tolerance, and performance monitoring.

## Technologies Used
- Java
- Spring Boot
- Netflix Eureka Server
- Keycloak
- Circuit Breaker pattern
- Zipkin
- Docker

## Features

### 1. Microservices Architecture
- Developed microservices for Customer Management and Account Management.
- Utilized Netflix Eureka Server for service discovery and load balancing, optimizing resource utilization and scalability within the architecture.

### 2. Enhanced Security and Reliability Measures
- Implemented an API Gateway to route requests to Customer Service and Account Service, ensuring streamlined request handling and reduced latency for improved system performance.
- Leveraged Keycloak for secure authentication and authorization, fortifying data security and implementing strict user access controls to safeguard sensitive information.

### 3. Fault Tolerance and Performance Monitoring
- Applied the Circuit Breaker pattern to enhance fault tolerance and system reliability, minimizing service disruptions during failures and maintaining consistent availability.
- Integrated Zipkin for distributed tracing capabilities, enabling comprehensive monitoring and diagnosis of performance issues across microservices. This integration improves system visibility and enhances troubleshooting capabilities, ensuring optimized system performance.

## Getting Started
To run the project locally, follow these steps:
1. Clone the repository.
2. Navigate to the project directory.
4. Access the application through the provided endpoints.
## API Endpoints

### Add Customer
- **Endpoint**: `POST localhost:8765/api/customer`
- **Description**: Add a customer to the database
- **Request Body**:
  ```json
  {
    "firstName": "manav",
    "lastName": "jha",
    "email": "manav@gmail.com",
    "address": "J-325 dilshad colony, Himachal Pradesh, India",
    "contactNumber": "88605099871"
  }
  ```

### Find All Customers
- **Endpoint**: `GET localhost:8765/api/customer`
- **Description**: Find all customers in the database

### Get Customer by ID
- **Endpoint**: `GET localhost:8765/api/customer/{customerId}`
- **Description**: Find customer with specific ID

### Update Customer
- **Endpoint**: `PUT localhost:8765/api/customer/{customerId}`
- **Description**: Update customer information with given ID
- **Request Body**:
  ```json
  {
    "firstName": "manavupdated",
    "email": "manav@gmail.com",
    "address": "J-325 dilshad colony, Himachal Pradesh, India",
    "contactNumber": "88605099871"
  }
  ```

### Create Account
- **Endpoint**: `POST localhost:8765/api/account/{customerId}`
- **Description**: Create an account for a customer
- **Request Body**:
  ```json
  {
    "accountType": "saving"
  }
  ```

### Add Amount
- **Endpoint**: `PUT localhost:8765/api/account/credit`
- **Description**: Add balance to an account
- **Request Body**:
  ```json
  {
    "customerId": 7,
    "accountNumber": "2701065352",
    "firstName": "manav",
    "lastName": "jha",
    "email": "manav@gmail.com",
    "Amount": 1202.60
  }
  ```

### Debit Amount
- **Endpoint**: `PUT localhost:8765/api/account/debit`
- **Description**: Withdraw amount from the bank
- **Request Body**:
  ```json
  {
    "customerId": 7,
    "accountNumber": "2701065352",
    "firstName": "manav",
    "lastName": "jha",
    "email": "manav@gmail.com",
    "amount": 1202.60
  }
  ```

### Get Account Details
- **Endpoint**: `GET localhost:8765/api/account/{customerId}/{accountNumber}`
- **Description**: Get details of an account

### Delete Account
- **Endpoint**: `DELETE localhost:8765/api/account/{accountNumber}`
- **Description**: Delete account using account number

### Delete Customer
- **Endpoint**: `DELETE localhost:8765/api/customer/{customerId}`
- **Description**: Delete customer with given ID

### Delete Account with Customer ID
- **Endpoint**: `DELETE localhost:8083/api/account/customer/{customerId}`
- **Description**: Delete account of a specific customer by customer ID
## Contributors
- Badal Jha

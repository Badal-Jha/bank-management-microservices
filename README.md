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
3. Run `docker-compose up` to start the services.
4. Access the application through the provided endpoints.

## Contributors
- [Your Name]
- [Other contributors]

## License
This project is licensed under the [License Name] License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgements
Special thanks to [Acknowledged Person/Team] for their contributions and support.

---
Feel free to customize the readme file further based on your specific project details and requirements.

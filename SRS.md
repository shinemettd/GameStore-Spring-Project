GameStore Backend Spring Java Project SRS

1. Introduction

This Software Requirements Specification (SRS) document outlines the requirements for the development of a backend system for a gaming platform. The system consists of two main services: User Service and Game Store Service.

2. User Service

2.1. User Management

Users must register with a unique email and a chosen password.
Users must have a constant nickname associated with their account.
Users can change their passwords using the PUT method.

2.2. Wallet Management

Each user has a wallet balance that they can use to purchase games from the store.
Users can check their wallet balance.
Admins can adjust user wallet balances, simulating transactions.

3. Game Store Service

3.1. Game Listing

The system must maintain a list of available games.
Each game should have a price ranging from 0 to any positive number.
Games must be categorized by genres using tags.

4. Admin Controls

Admins have access to functionalities such as adjusting user wallet balances and blocking users from accessing specific games.
Admins can view transaction logs for auditing purposes.
Admins can add/delete new games to the store.
(will be added with Spring Security)

5. Security Considerations

The system must enforce secure authentication mechanisms for user access.
Sensitive operations, such as adjusting wallet balances and blocking users, should require admin privileges.

6. Performance Considerations

The system should be designed to handle concurrent user requests efficiently.
Database queries and operations should be optimized for performance.

7. Technology Stack

Backend: Spring Framework 6.0.х
Database: H2/PostgreSQL/MySQL
Authentication: JWT (In brief future)
Frontend: Thymeleaf or Vue.js (If it will be possible)

8. Conclusion

This SRS document outlines the requirements for developing the backend system for the gaming platform. It includes features such as user management, wallet management, game listing, discounts, admin controls, security considerations, performance considerations, and the technology stack to be used.


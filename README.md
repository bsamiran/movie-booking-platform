# Movie Booking Platform (Spring Boot + Kafka)
## Overview
This project demonstrates a scalable, secure, and maintainable movie booking system using microservices architecture with Spring Boot, Apache Kafka, and JWT-based authentication.
Auth Service → Handles user signup/signin and issues JWT tokens.
Booking Service → Handles ticket bookings and publishes booking events. Requires JWT for all APIs.
Theatre Service → Manages theatres, shows, and seat availability. Consumes booking events to update seat counts. Requires JWT for all APIs.
Kafka → Provides asynchronous communication between services.
Database → In-memory H2 database for persistence and testing.

## Problem
Traditional monolithic booking systems:
Are tightly coupled and hard to scale.
Struggle with concurrency (multiple users booking at once).
Have poor maintainability.
Lack proper authentication and security.

## Solution
We split functionality into microservices and use Kafka for event-driven communication.
Security is enforced using Spring Security + JWT:
Users must first signup/signin via Auth Service.
Auth Service issues a JWT token.
Booking and Theatre services validate JWT tokens before allowing access.
/h2-console/** endpoints are permitted for debugging, but all other APIs require a valid token.

## Security Flow
User calls Auth API → Auth Service saves user and issues JWT.
User includes JWT in Authorization: Bearer <token> header.
Booking/Theatre services validate JWT via a custom filter.
Only valid tokens allow access to APIs.
Invalid/missing tokens return 401 Unauthorized with JSON error.



### Architecture Diagram
+-------------------+         +-------------------+
|   Booking Service |         |   Theatre Service |
|  (Spring Boot)    |         |  (Spring Boot)    |
|-------------------|         |-------------------|
| - REST API (JWT)  |         | - REST API (JWT)  |
| - Publishes event |         | - Consumes event  |
+---------+---------+         +---------+---------+
          |                             |
          | Kafka Topic: booking-events |
          v                             v
+-----------------------------------------------+
|                Apache Kafka                   |
|   (Message Broker, async communication)       |
+-----------------------------------------------+
                          |
                          v
+-----------------------------------------------+
|                  H2 Database                  |
|   (Stores Theatre & Show seat availability)   |
+-----------------------------------------------+

+-------------------+
|   Auth Service    |
|  (Spring Boot)    |
|-------------------|
| - Signup/Signin   |
| - Issues JWT      |
+-------------------+


Flow:
1. User calls Booking API → Booking Service saves booking.
2. Booking Service publishes event → Kafka topic `booking-events`.
3. Theatre Service consumes event → updates seat count in DB.
4. User calls Theatre API → Theatre Service returns updated show info.


## Tech Stack
Java 17
Spring Boot 3.x
Spring Security 6.x + JWT
Spring Data JPA + H2 Database
Apache Kafka 3.9.1
Postman for API testing

### Kafka Setup
Start Zookeeper:cmd
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
Start Kafka broker:cmd
bin\windows\kafka-server-start.bat config\server.properties
Consume topic for Checking Purpose:cmd
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic booking-events

## APIs
Auth Service (port 8082)
Signup → POST /auth/signup?username=Ram&password=12345

Signin → POST /auth/signin?username=Ram&password=12345

Response: JWT token string

Theatre Service (port 8080)
GET /shows/browse?movieName=Avatar&city=Bangalore
Requires Authorization: Bearer <token> header
Response:

json
[
  {
    "id": 1,
    "movieName": "Avatar",
    "language": "English",
    "genre": "Sci-Fi",
    "showTime": "2026-02-05T14:00:00",
    "availableSeats": 97,
    "theatreName": "PVR Koramangala",
    "city": "Bangalore"
  }
]

Get Theatre Details 
GET /theatres/{id}
Requires Authorization: Bearer <token> header
Response:

json
{
  "id": 1,
  "name": "PVR Koramangala",
  "city": "Bangalore",
  "shows": [
    {
      "id": 1,
      "movieName": "Avatar",
      "language": "English",
      "genre": "Sci-Fi",
      "showTime": "2026-02-05T14:00:00",
      "availableSeats": 97
    }
  ]
}

Booking Service (port 8081)
Book Tickets
POST /bookings/book?showId=1&tickets=3&pricePerTicket=200&showTime=2026-02-05T14:00:00
Requires Authorization: Bearer <token> header
Response:

json
{
  "id": 1,
  "showId": 1,
  "ticketsBooked": 3,
  "totalPrice": 600.0,
  "bookingTime": "2026-02-04T23:08:50.9624514"
}

## Database
H2 in-memory DB
Access via: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:theatre-db
Example queries:
sql
SELECT * FROM THEATRE;
SELECT * FROM SHOW;

## Testing Flow
Start Kafka (Zookeeper + Broker).
Run all services (Auth, Booking, Theatre).
Signup + Signin in Auth Service → get JWT.
Call Booking/Theatre APIs with JWT in header.
Booking Service publishes event → Kafka topic booking-events.
Theatre Service consumes event → updates DB.
Verify via Theatre API or H2 console.
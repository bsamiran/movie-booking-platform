# Movie Booking Platform (Spring Boot + Kafka)
## Overview
This project demonstrates a scalable, maintainable movie booking system using microservices architecture with Spring Boot and Apache Kafka.
Booking Service → Handles ticket bookings and publishes booking events.
Theatre Service → Manages theatres, shows, and seat availability. Consumes booking events to update seat counts.
Kafka → Provides asynchronous communication between services.
Database → In-memory H2 database for persistence and testing.

## Problem
Traditional monolithic booking systems:
Are tightly coupled and hard to scale.
Struggle with concurrency (multiple users booking at once).
Have poor maintainability.

## Solution
We split functionality into microservices and use Kafka for event-driven communication:
Booking service publishes events (booking-events topic).
Theatre service consumes events and updates seat availability.
Services are loosely coupled, scalable, and resilient.



### Architecture Diagram
+-------------------+         +-------------------+
|   Booking Service |         |   Theatre Service |
|  (Spring Boot)    |         |  (Spring Boot)    |
|-------------------|         |-------------------|
| - REST API        |         | - REST API        |
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

Flow:
1. User calls Booking API → Booking Service saves booking.
2. Booking Service publishes event → Kafka topic `booking-events`.
3. Theatre Service consumes event → updates seat count in DB.
4. User calls Theatre API → Theatre Service returns updated show info.


## Tech Stack
Java 17
Spring Boot 3.x
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
Theatre Service (port 8080)
GET /shows/browse?movieName=Avatar&city=Bangalore
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
Run both services.
Hit booking API → booking-service publishes event.
Kafka console consumer shows event:
json
{"showId":1,"ticketsBooked":3}
Theatre-service consumes event → updates DB.
Verify via browse API or H2 console.
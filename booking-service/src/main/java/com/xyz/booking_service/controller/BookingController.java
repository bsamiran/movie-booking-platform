package com.xyz.booking_service.controller;

import com.xyz.booking_service.entity.Booking;
import com.xyz.booking_service.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public Booking bookTickets(@RequestParam Long showId,
                               @RequestParam int tickets,
                               @RequestParam double pricePerTicket,
                               @RequestParam String showTime) {
        LocalDateTime parsedShowTime = LocalDateTime.parse(showTime);
        return bookingService.bookTickets(showId, tickets, pricePerTicket, parsedShowTime);
    }
}

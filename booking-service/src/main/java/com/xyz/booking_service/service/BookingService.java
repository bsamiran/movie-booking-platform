package com.xyz.booking_service.service;

import com.xyz.booking_service.entity.Booking;
import com.xyz.booking_service.kafka.BookingProducer;
import com.xyz.booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    // add field
    private final BookingProducer bookingProducer;

    // constructor injection
    public BookingService(BookingRepository bookingRepository, BookingProducer bookingProducer) {
        this.bookingRepository = bookingRepository;
        this.bookingProducer = bookingProducer;
    }

    public Booking bookTickets(Long showId, int tickets, double pricePerTicket, LocalDateTime showTime) {
        double totalPrice = tickets * pricePerTicket;
        if (tickets >= 3) totalPrice -= pricePerTicket * 0.5;
        if (showTime.getHour() >= 12 && showTime.getHour() < 17) totalPrice *= 0.8;

        Booking booking = new Booking();
        booking.setShowId(showId);
        booking.setNumberOfTickets(tickets);
        booking.setTotalPrice(totalPrice);
        booking.setBookingTime(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);

        // publish Kafka event
        bookingProducer.sendBookingEvent(new com.xyz.common_dto.dto.BookingEvent(showId, tickets));

        return saved;
    }

}

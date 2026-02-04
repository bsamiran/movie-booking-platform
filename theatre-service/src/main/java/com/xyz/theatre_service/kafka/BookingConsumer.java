package com.xyz.theatre_service.kafka;

import com.xyz.common_dto.events.BookingEvent;
import com.xyz.theatre_service.entity.Show;
import com.xyz.theatre_service.repository.ShowRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingConsumer {
    private final ShowRepository showRepository;

    public BookingConsumer(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @KafkaListener(topics = "booking-events", groupId = "theatre-service-group")
    public void consumeBookingEvent(BookingEvent event) {
        System.out.println("Received booking event for showId=" + event.getShowId());
        Optional<Show> showOpt = showRepository.findById(event.getShowId());
        if (showOpt.isPresent()) {
            Show show = showOpt.get();
            show.setAvailableSeats(show.getAvailableSeats() - event.getTicketsBooked());
            showRepository.save(show);
            System.out.println("Updated seats for show " + show.getId() +
                    " new available=" + show.getAvailableSeats());
        }
    }
}

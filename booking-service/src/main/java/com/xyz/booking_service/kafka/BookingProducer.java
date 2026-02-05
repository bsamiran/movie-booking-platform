package com.xyz.booking_service.kafka;

import com.xyz.common_dto.dto.BookingEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingProducer {
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingProducer(KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(BookingEvent event) {
        kafkaTemplate.send("booking-events", event);
        System.out.println("Published booking event: showId=" + event.getShowId() +
                ", tickets=" + event.getTicketsBooked());
    }
}

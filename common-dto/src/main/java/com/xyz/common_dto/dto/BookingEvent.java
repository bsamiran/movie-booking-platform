package com.xyz.common_dto.dto;

public class BookingEvent {
    private Long showId;
    private int ticketsBooked;

    public BookingEvent() {}
    public BookingEvent(Long showId, int ticketsBooked) {
        this.showId = showId;
        this.ticketsBooked = ticketsBooked;
    }
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public int getTicketsBooked() { return ticketsBooked; }
    public void setTicketsBooked(int ticketsBooked) { this.ticketsBooked = ticketsBooked; }
}

// ShowSummaryDTO.java
package com.xyz.common_dto.dto;

import java.time.LocalDateTime;

public class ShowSummaryDTO {
    private Long id;
    private String movieName;
    private String language;
    private String genre;
    private LocalDateTime showTime;
    private int availableSeats;

    public ShowSummaryDTO(Long id, String movieName, String language, String genre,
                          LocalDateTime showTime, int availableSeats) {
        this.id = id;
        this.movieName = movieName;
        this.language = language;
        this.genre = genre;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

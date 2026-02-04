// ShowDTO.java
package com.xyz.common_dto.dto;

import java.time.LocalDateTime;

public class ShowDTO {
    private Long id;
    private String movieName;
    private String language;
    private String genre;
    private LocalDateTime showTime;
    private String theatreName;
    private String city;
    private int availableSeats;

    // constructor
    public ShowDTO(Long id, String movieName, String language, String genre,
                   LocalDateTime showTime, int availableSeats,
                   String theatreName, String city) {
        this.id = id;
        this.movieName = movieName;
        this.language = language;
        this.genre = genre;
        this.showTime = showTime;
        this.availableSeats = availableSeats;
        this.theatreName = theatreName;
        this.city = city;
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

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

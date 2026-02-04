// TheatreDTO.java
package com.xyz.common_dto.dto;

import java.util.List;

public class TheatreDTO {
    private Long id;
    private String name;
    private String city;
    private List<ShowSummaryDTO> shows;

    public TheatreDTO(Long id, String name, String city, List<ShowSummaryDTO> shows) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.shows = shows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ShowSummaryDTO> getShows() {
        return shows;
    }

    public void setShows(List<ShowSummaryDTO> shows) {
        this.shows = shows;
    }
}

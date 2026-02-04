// TheatreService.java
package com.xyz.theatre_service.service;

import com.xyz.theatre_service.entity.Theatre;
import com.xyz.theatre_service.repository.TheatreRepository;
import com.xyz.common_dto.dto.TheatreDTO;
import com.xyz.common_dto.dto.ShowSummaryDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public TheatreDTO getTheatreDetails(Long id) {
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        return new TheatreDTO(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity(),
                theatre.getShows().stream()
                        .map(show -> new ShowSummaryDTO(
                                show.getId(),
                                show.getMovieName(),
                                show.getLanguage(),
                                show.getGenre(),
                                show.getShowTime(),
                                show.getAvailableSeats()
                        ))
                        .collect(Collectors.toList())
        );
    }
}

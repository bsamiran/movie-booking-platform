// ShowService.java
package com.xyz.theatre_service.service;

import com.xyz.theatre_service.entity.Show;
import com.xyz.theatre_service.repository.ShowRepository;
import com.xyz.common_dto.dto.ShowDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<ShowDTO> browseShows(String movieName, String city) {
        List<Show> shows = showRepository.findByMovieNameAndTheatre_City(movieName, city);
        return shows.stream()
                .map(show -> new ShowDTO(
                        show.getId(),
                        show.getMovieName(),
                        show.getLanguage(),
                        show.getGenre(),
                        show.getShowTime(),
                        show.getAvailableSeats(),
                        show.getTheatre().getName(),
                        show.getTheatre().getCity()
                ))
                .collect(Collectors.toList());
    }
}

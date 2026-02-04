package com.xyz.theatre_service.repository;

import com.xyz.theatre_service.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieNameAndTheatre_City(String movieName, String city);
}

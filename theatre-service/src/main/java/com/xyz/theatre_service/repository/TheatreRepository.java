package com.xyz.theatre_service.repository;

import com.xyz.theatre_service.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // You can add custom query methods if needed, for example:
    Theatre findByNameAndCity(String name, String city);
}

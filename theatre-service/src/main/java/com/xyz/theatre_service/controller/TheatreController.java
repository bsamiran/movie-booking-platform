// TheatreController.java
package com.xyz.theatre_service.controller;

import com.xyz.common_dto.dto.TheatreDTO;
import com.xyz.theatre_service.service.TheatreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/{id}")
    public TheatreDTO getTheatreDetails(@PathVariable Long id) {
        return theatreService.getTheatreDetails(id);
    }
}

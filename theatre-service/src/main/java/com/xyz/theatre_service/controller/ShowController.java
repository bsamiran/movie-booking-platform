// ShowController.java
package com.xyz.theatre_service.controller;

import com.xyz.common_dto.dto.ShowDTO;
import com.xyz.theatre_service.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/browse")
    public List<ShowDTO> browseShows(@RequestParam String movieName,
                                     @RequestParam String city) {
        return showService.browseShows(movieName, city);
    }
}

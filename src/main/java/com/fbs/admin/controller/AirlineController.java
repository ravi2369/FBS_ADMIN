package com.fbs.admin.controller;

import com.fbs.admin.model.dto.AirlineDTO;
import com.fbs.admin.service.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/airline")
@Slf4j
public class AirlineController {
    private AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addAirline(@RequestBody AirlineDTO airlineDTO) {
        log.info("{}--> inside addAirport() <---{}" + airlineDTO);
        try {
            return ResponseEntity.ok().body(airlineService.addAirline(airlineDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/modify")
    public ResponseEntity modifyAirline(@RequestBody AirlineDTO airlineDTO) {
        log.info("{}--> inside modifyAirline() <---{}" + airlineDTO);
        try {
            return ResponseEntity.ok().body(airlineService.updateAirline(airlineDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity getAirline(@RequestParam(required = false) String airlineCode) {
        log.info("{}--> inside getAirline() <---{}" + airlineCode);
        try {
            return ResponseEntity.ok().body(airlineService.findAirline(airlineCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/viewall")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(airlineService.findAllAirlines());
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeAirline(@RequestParam String airlineCode) {
        log.info("{}--> inside removeAirline() <---{}" + airlineCode);
        try {
            return ResponseEntity.ok().body(airlineService.deleteAirline(airlineCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

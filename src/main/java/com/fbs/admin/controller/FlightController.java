package com.fbs.admin.controller;

import com.fbs.admin.model.Flight;
import com.fbs.admin.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/flight")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping(value = "/add")
    public ResponseEntity addFlight(@RequestBody Flight flight) {
        log.info("{}--> inside addFlight() <---{}" + flight);
        try {
            return ResponseEntity.ok().body(flightService.addFlight(flight));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/modify")
    public ResponseEntity modifyFlight(@RequestBody Flight flight) {
        log.info("{}--> inside modifyFlight() <---{}" + flight);
        try {
            return ResponseEntity.ok().body(flightService.updateFlight(flight));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/view")
    public ResponseEntity getFlight(@RequestParam String flightNumber) {
        log.info("{}--> inside getFlight() <---{}" + flightNumber);
        try {
            return ResponseEntity.ok().body(flightService.findFlight(flightNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/viewall")
    public ResponseEntity getAllFlights() {
        return ResponseEntity.ok().body(flightService.findAllFlights());
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity removeFlight(@RequestParam String flightNumber) {
        log.info("{}--> inside removeFlight() <---{}" + flightNumber);
        try {
            return ResponseEntity.ok().body(flightService.deleteFlights(flightNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.fbs.admin.controller;

import com.fbs.admin.model.dto.FlightScheduleDTO;
import com.fbs.admin.service.AirlineService;
import com.fbs.admin.service.FlightScheduleService;
import com.fbs.admin.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/schedule")
@Slf4j
public class FlightScheduleController {

    private FlightScheduleService flightScheduleService;
    private AirlineService airlineService;
    private FlightService flightService;

    @Autowired
    public FlightScheduleController(FlightScheduleService flightScheduleService, AirlineService airlineService, FlightService flightService) {
        this.flightScheduleService = flightScheduleService;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO) {
        log.info("{}--> inside addFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.addFlightSchedule(flightScheduleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modifyFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO) {
        log.info("{}--> inside modifyFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.updateFlightSchedule(flightScheduleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity deleteFlightSchedule(@RequestParam String id) {
        log.info("{}--> inside deleteFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.deleteFlightSchedule(Long.valueOf(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity searchFlightSchedule(@RequestParam String fromLocation, @RequestParam String toLocation, @RequestParam String startDateTime) {
        log.info("{}--> inside searchFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.findFlightSchedule(fromLocation, toLocation, startDateTime));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/view/all")
    public ResponseEntity searchAllFlightSchedule() {
        return ResponseEntity.ok().body(flightScheduleService.findAllFlightSchedules());
    }
}

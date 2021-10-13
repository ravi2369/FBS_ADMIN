package com.fbs.admin.controller;

import com.fbs.admin.model.FlightSchedule;
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
    public ResponseEntity addFlightSchedule(@RequestBody FlightSchedule flightSchedule) {
        log.info("{}--> inside addFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.addFlightSchedule(flightSchedule));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity modifyFlightSchedule(@RequestBody FlightSchedule flightSchedule, @RequestParam String startDateTime, @RequestParam String endDateTime) {
        log.info("{}--> inside modifyFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.updateFlightSchedule(flightSchedule));
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
    public ResponseEntity searchFlightSchedule(@RequestParam String flightId) {
        log.info("{}--> inside deleteFlightSchedule() <---{}");
        try {
            return ResponseEntity.ok().body(flightScheduleService.findFlightSchedule(Long.valueOf(flightId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/view/all")
    public ResponseEntity searchAllFlightSchedule() {
        return ResponseEntity.ok().body(flightScheduleService.findAllFlightSchedules());
    }
}

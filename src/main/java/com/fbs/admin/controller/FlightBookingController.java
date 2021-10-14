package com.fbs.admin.controller;

import com.fbs.admin.model.dto.BookTicketDTO;
import com.fbs.admin.service.BookTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
@RequestMapping(value = "/flight/booking")
public class FlightBookingController {

    @Autowired
    private BookTicketService bookTicketService;

    @PostMapping(value = "/ticket/booking")
    public ResponseEntity book(@PathParam(value = "") String flightNumber, @RequestBody BookTicketDTO bookTicketDTO) {
        try {
            return ResponseEntity.ok().body(bookTicketService.bookTicket(flightNumber, bookTicketDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.fbs.admin.controller;

import com.fbs.admin.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/flight/booking")
public class FlightBookingController {
    @Autowired
    private FlightRepository flightRepository;


   /* @RequestMapping(value = "/bookFlight", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String bookFlight(BookingRequest bookingRequest) {
        log.info("bookFlight() invoked with the BookingRequest: " + bookingRequest.toString());
        BookFlight bookFlight = flightBookingService.bookFlight(bookingRequest);
        return "reservation/reservationConfirmation";
    }*/
}

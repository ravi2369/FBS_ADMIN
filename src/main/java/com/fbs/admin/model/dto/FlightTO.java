package com.fbs.admin.model.dto;

import lombok.Data;

@Data
public class FlightTO {

    private String flightNumber;

    private String operatingAirlines;

    private String departureCity;

    private String arrivalCity;

    private String flightModel;

    private String dateOfDeparture;

    private String estimatedDepartureTime;

    private String meal;
}

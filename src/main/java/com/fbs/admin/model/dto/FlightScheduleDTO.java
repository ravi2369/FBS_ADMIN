package com.fbs.admin.model.dto;

import lombok.Data;

@Data
public class FlightScheduleDTO {
    private Long id;
    private String flightNumber;
    private String airLineCode;
    private String startDateTime;
    private String endDataTime;
    private String fromLocation;
    private String toLocation;
}

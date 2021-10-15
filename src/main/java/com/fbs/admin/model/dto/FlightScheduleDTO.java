package com.fbs.admin.model.dto;

import lombok.Data;

@Data
public class FlightScheduleDTO {
    private Long flightScheduleId;
    private String flightNumber;
    private String airLineCode;
    private String startDateTime;
    private String endDateTime;
    private String fromLocation;
    private String toLocation;
}

package com.fbs.admin.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightScheduleDTO {
    private Long id;
    private String flightNumber;
    private String airLineCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDataTime;
    private String fromLocation;
    private String toLocation;
}

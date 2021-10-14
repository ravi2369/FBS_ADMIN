package com.fbs.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties("flight")
@Table(name = "flight_schedule")
@Data
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String airLineCode;
    @JsonIgnore
    private String airLineName;
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Flight flight;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String fromLocation;
    private String toLocation;
    private String flightModel;
    private String meal;
    private Double ticketPrice;
}

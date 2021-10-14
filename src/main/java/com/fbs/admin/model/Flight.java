package com.fbs.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("airline")
@Table(name = "FLIGHT")
@Data
public class Flight {
    @Id
    private String flightNumber;
    private String flightModel;
    private int availableSeats;
    private String meal;
    private Double ticketPrice;
    @ManyToOne
    @JoinColumn(name = "air_line_code")
    @JsonIgnore
    private Airline airline;
    private String fromLocation;
    private String toLocation;
}

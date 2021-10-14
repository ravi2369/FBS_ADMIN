package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String flightNumber;
    private String flightModel;
    private String airlineCode;
    private int availableSeats;
    private String meal;
    private Double ticketPrice;
}

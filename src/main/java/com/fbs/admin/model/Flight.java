package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {
    @Id
    private String flightNumber;
    private String flightModel;
    private String fromLocation;
    private String airLineCode;
    private String toLocation;
    private int availableSeats;
    private String meal;
}

package com.fbs.admin.model.dto;

import com.fbs.admin.model.Passenger;
import lombok.Data;

import java.util.List;

@Data
public class BookTicketDTO {
    private Long id;
    private String departureDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    private List<Passenger> passengers;
}

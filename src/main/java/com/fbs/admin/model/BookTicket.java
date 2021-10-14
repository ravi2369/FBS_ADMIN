package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "book_ticket")
@Data
public class BookTicket {
    @Id
    private Long id;
    private String PNR;
    private String departureDate;
    private String fromPlace;
    private String toPlace;
    private String email;
    private String name;
    private int seats;
    private String meal;
    @OneToMany
    private List<Passenger> passengers;
}

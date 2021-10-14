package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
@Data
public class Passenger {
    @Id
    private Long id;
    private String passengerName;
    private int age;
    private String gender;
}

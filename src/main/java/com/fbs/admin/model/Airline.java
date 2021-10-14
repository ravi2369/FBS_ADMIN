package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "airline")
@Data
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String airLineCode;
    private String airLineName;
    @Column(name = "status", columnDefinition = "Boolean default false")
    private Boolean status;
}

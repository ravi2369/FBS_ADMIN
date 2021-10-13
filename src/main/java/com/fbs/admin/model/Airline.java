package com.fbs.admin.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airline")
@Data
public class Airline {
    @Id
    private String airLineCode;
    private String airLineName;
    @Column(name = "status", columnDefinition = "Boolean default false")
    private Boolean status;
}

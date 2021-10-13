package com.fbs.admin.model;

import lombok.Data;

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
    private Boolean status;
}

package com.fbs.admin.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Data
public class Schedule extends AbstractEntityModel {
    //@OneToOne(fetch = FetchType.EAGER)
    private String fromPlace;
    //@OneToOne(fetch = FetchType.EAGER)
    private String toPlace;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String meal;
}

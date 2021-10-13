package com.fbs.admin.service;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.FlightSchedule;
import com.fbs.admin.model.dto.FlightScheduleDTO;

import java.util.List;

public interface FlightScheduleService {
    FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    FlightSchedule updateFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    String deleteFlightSchedule(Long id) throws FBSException;

    FlightSchedule findFlightSchedule(String fromLocation, String toLocation, String startDateTime) throws FBSException;

    List<FlightSchedule> findAllFlightSchedules();
}

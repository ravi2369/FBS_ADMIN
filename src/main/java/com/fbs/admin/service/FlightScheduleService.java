package com.fbs.admin.service;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.FlightSchedule;
import com.fbs.admin.model.dto.FlightScheduleDTO;

public interface FlightScheduleService {
    FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    FlightSchedule updateFlightSchedule(FlightScheduleDTO flightScheduleDTO) throws FBSException;

    String deleteFlightSchedule(Long id) throws FBSException;

    FlightSchedule findFlightSchedule(Long id) throws FBSException;

    Iterable<FlightSchedule> findAllFlightSchedules();
}

package com.fbs.admin.service;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.FlightSchedule;

public interface FlightScheduleService {
    FlightSchedule addFlightSchedule(FlightSchedule flightSchedule, String startDateTime, String endDateTime) throws FBSException;

    FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule, String startDateTime, String endDateTime) throws FBSException;

    String deleteFlightSchedule(Long id) throws FBSException;

    FlightSchedule findFlightSchedule(Long id) throws FBSException;

    Iterable<FlightSchedule> findAllFlightSchedules();
}

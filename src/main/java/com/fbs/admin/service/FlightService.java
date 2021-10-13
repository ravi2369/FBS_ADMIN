package com.fbs.admin.service;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Flight;

public interface FlightService {
    Flight addFlight(Flight flight) throws FBSException;

    Flight updateFlight(Flight flight) throws FBSException;

    Flight findFlight(String flightNumber) throws FBSException;

    Iterable<Flight> findAllFlights();

    String deleteFlights(String flightNumber) throws FBSException;
}

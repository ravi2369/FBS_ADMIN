package com.fbs.admin.service;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Airline;
import com.fbs.admin.model.dto.AirlineDTO;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    Airline addAirline(AirlineDTO airlineDTO) throws FBSException;

    Airline updateAirline(AirlineDTO airlineDTO) throws FBSException;

    Optional<AirlineDTO> findAirline(String airLineCode) throws FBSException;

    List<AirlineDTO> findAllAirlines();

    String deleteAirline(String airportCode) throws FBSException;
}

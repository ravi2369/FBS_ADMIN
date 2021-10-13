package com.fbs.admin.service.serviceImpl;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Flight;
import com.fbs.admin.repository.FlightRepository;
import com.fbs.admin.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }

    @Override
    public Flight addFlight(Flight flight) {
        if (flight.getFlightNumber() != null) {
            Optional<Flight> findById = flightRepository.findById(flight.getFlightNumber());
            if (!findById.isPresent()) {
                return flightRepository.save(flight);
            } else
                throw new FBSException("Flight with number: " + flight.getFlightNumber() + " already present");
        } else {
            throw new FBSException("Flight Number must not be empty");
        }
    }

    @Override
    public Flight updateFlight(Flight flight) {
        if (flight.getFlightNumber() != null) {
            Optional<Flight> findById = flightRepository.findById(flight.getFlightNumber());
            if (findById.isPresent()) {
                return flightRepository.save(flight);
            } else
                throw new FBSException("Flight with number: " + flight.getFlightNumber() + " not exists");
        } else {
            throw new FBSException("Flight Number must not be empty");
        }
    }

    @Override
    public Flight findFlight(String flightNumber) {
        if (flightNumber != null) {
            Optional<Flight> findById = flightRepository.findById(flightNumber);
            if (findById.isPresent()) {
                return findById.get();
            } else
                throw new FBSException("Flight with flightNumber code: " + flightNumber + " not exists");
        } else {
            throw new FBSException("Flight Number must not be empty");
        }

    }

    @Override
    public Iterable<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public String deleteFlights(String flightNumber) {
        if (flightNumber != null) {
            Optional<Flight> findById = flightRepository.findById(flightNumber);
            if (findById.isPresent()) {
                flightRepository.deleteById(flightNumber);
                return "Airline removed";
            } else
                throw new FBSException("Flight with code : " + flightNumber + " not exists");
        } else {
            throw new FBSException("Flight Number must not be empty");
        }
    }
}

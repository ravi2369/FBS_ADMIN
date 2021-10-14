package com.fbs.admin.service.serviceImpl;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Flight;
import com.fbs.admin.model.FlightSchedule;
import com.fbs.admin.model.dto.AirlineDTO;
import com.fbs.admin.model.dto.FlightScheduleDTO;
import com.fbs.admin.repository.FlightScheduleRepository;
import com.fbs.admin.service.AirlineService;
import com.fbs.admin.service.FlightScheduleService;
import com.fbs.admin.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.fbs.admin.util.DateUtility.convertToFbsFormat;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private FlightScheduleRepository flightScheduleRepository;

    private AirlineService airlineService;
    private FlightService flightService;

    @Autowired
    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository, AirlineService airlineService, FlightService flightService) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @Override
    public FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO) {
        if (flightScheduleDTO.getAirLineCode() != null && flightScheduleDTO.getFlightNumber() != null) {
            Optional<FlightSchedule> fs = flightScheduleRepository.findFlightSchedule(flightScheduleDTO.getAirLineCode(), flightScheduleDTO.getFlightNumber());
            if (fs.isPresent()) {
                throw new FBSException("Flight schedule is already available for this " + fs.get().getAirLineCode() + " and " + fs.get().getFlightNumber());
            }
            FlightSchedule flightSchedule = new FlightSchedule();
            Optional<AirlineDTO> airline = (airlineService.findAirline(flightScheduleDTO.getAirLineCode()));
            if (airline != null && airline.get().getStatus() != null) {
                if (airline.get().getStatus().toString().equalsIgnoreCase("Unblock")) {
                    Flight flight = flightService.findFlight(flightScheduleDTO.getFlightNumber());
                    flightSchedule.setFlightNumber(flight.getFlightNumber());
                    flightSchedule.setAirLineCode(airline.get().getAirLineCode());
                    flightSchedule.setAirLineName(airline.get().getAirLineName());
                    flightSchedule.setToLocation(flightScheduleDTO.getToLocation());
                    flightSchedule.setFromLocation(flightScheduleDTO.getFromLocation());
                    flight.setToLocation(flightScheduleDTO.getToLocation());
                    flight.setFromLocation(flightScheduleDTO.getFromLocation());
                    flightSchedule.setTicketPrice(flight.getTicketPrice());
                    if (flightScheduleDTO.getStartDateTime() != null) {
                        flightSchedule.setStartDateTime((LocalDateTime) convertToFbsFormat(flightScheduleDTO.getStartDateTime()));
                    }
                    if (flightScheduleDTO.getEndDateTime() != null) {
                        flightSchedule.setEndDateTime((LocalDateTime) convertToFbsFormat(flightScheduleDTO.getEndDateTime()));
                    }
                    flightSchedule.setFlightModel(flight.getFlightModel());
                    flightSchedule.setMeal(flight.getMeal());
                    flightSchedule.setFlight(flight);
                    return flightScheduleRepository.save(flightSchedule);

                } else {
                    throw new FBSException("Airline with this " + airline.get().getAirLineCode() + " code is Blocked, please choose another airline");
                }
            } else {
                throw new FBSException("Airline with code : " + airline.get().getAirLineCode() + " not exists");
            }
        } else {
            throw new FBSException("Airline Code & Flight Number must be required to schedule flight");
        }
    }

    @Override
    public FlightSchedule updateFlightSchedule(FlightScheduleDTO flightScheduleDTO) {
        if (flightScheduleDTO.getId() != null && flightScheduleDTO.getAirLineCode() != null && flightScheduleDTO.getFlightNumber() != null) {
            Optional<FlightSchedule> updateScheduleFlight = flightScheduleRepository.findById(flightScheduleDTO.getId());
            if (updateScheduleFlight.isPresent()) {
                Optional<FlightSchedule> fs = flightScheduleRepository.findFlightSchedule(flightScheduleDTO.getAirLineCode(), flightScheduleDTO.getFlightNumber());
                if (!fs.isPresent()) {
                    throw new FBSException("Flight schedule is not available for this " + fs.get().getAirLineCode() + " and " + fs.get().getFlightNumber());
                }
                Optional<AirlineDTO> airline = (airlineService.findAirline(flightScheduleDTO.getAirLineCode()));
                if (airline != null) {
                    if (airline.get().getStatus().toString().equalsIgnoreCase("Unblock")) {
                        Flight flight = flightService.findFlight(flightScheduleDTO.getFlightNumber());
                        updateScheduleFlight.get().setFlightNumber(flight.getFlightNumber());
                        updateScheduleFlight.get().setAirLineCode(airline.get().getAirLineCode());
                        updateScheduleFlight.get().setAirLineName(airline.get().getAirLineName());
                        updateScheduleFlight.get().setToLocation(flightScheduleDTO.getToLocation());
                        updateScheduleFlight.get().setFromLocation(flightScheduleDTO.getFromLocation());
                        updateScheduleFlight.get().setTicketPrice(flight.getTicketPrice());
                        flight.setToLocation(flightScheduleDTO.getToLocation());
                        flight.setFromLocation(flightScheduleDTO.getFromLocation());
                        if (flightScheduleDTO.getStartDateTime() != null) {
                            updateScheduleFlight.get().setStartDateTime((LocalDateTime) convertToFbsFormat(flightScheduleDTO.getStartDateTime()));
                        }
                        if (flightScheduleDTO.getEndDateTime() != null) {
                            updateScheduleFlight.get().setEndDateTime((LocalDateTime) convertToFbsFormat(flightScheduleDTO.getEndDateTime()));
                        }
                        updateScheduleFlight.get().setFlightModel(flight.getFlightModel());
                        updateScheduleFlight.get().setMeal(flight.getMeal());
                        updateScheduleFlight.get().setFlight(flight);
                        return flightScheduleRepository.save(updateScheduleFlight.get());
                    } else {
                        throw new FBSException("Airline with this " + airline.get().getAirLineCode() + " code is Blocked, please take another airline");
                    }
                } else {
                    throw new FBSException("Airline with code : " + airline.get().getAirLineCode() + " not exists");
                }
            } else {
                throw new FBSException("Scheduled Airline is not found with this " + flightScheduleDTO.getId() + " id and " + flightScheduleDTO.getFlightNumber() + "," + flightScheduleDTO.getAirLineCode() + " Please schedule once again");
            }
        } else {
            throw new FBSException("Scheduled Id " + flightScheduleDTO.getId() + " Flight Number " + flightScheduleDTO.getFlightNumber() + " Airline Code " + flightScheduleDTO.getAirLineCode());
        }
    }

    @Override
    public String deleteFlightSchedule(Long id) throws FBSException {
        if (id == null) {
            throw new FBSException("Schedule Flight Id Required");
        }
        Optional<FlightSchedule> flightSchedule = flightScheduleRepository.findById(id);
        if (!flightSchedule.isPresent()) {
            throw new FBSException("Schedule Flight Id Required, Please Enter a valid Flight Id");
        } else {
            flightScheduleRepository.deleteById(id);
        }
        return "Scheduled flight with Id " + id + " is deleted";
    }

    @Override
    public List<FlightSchedule> findFlightSchedule(String fromLocation, String toLocation) throws FBSException {
        if (fromLocation != null && toLocation != null) {
            List<FlightSchedule> flightSchedule = flightScheduleRepository.findScheduledFlights(fromLocation, toLocation);
            if (!flightSchedule.isEmpty()) {
                return flightSchedule;
            } else
                throw new FBSException("flight schedule is not available");
        } else {
            throw new FBSException("Please select  fromLocation & toLocation");
        }
    }

    @Override
    public List<FlightSchedule> findAllFlightSchedules() {
        return flightScheduleRepository.findAll();
    }
}

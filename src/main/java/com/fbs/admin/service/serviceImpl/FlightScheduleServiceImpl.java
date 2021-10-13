package com.fbs.admin.service.serviceImpl;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Flight;
import com.fbs.admin.model.FlightSchedule;
import com.fbs.admin.model.dto.AirlineDTO;
import com.fbs.admin.repository.FlightScheduleRepository;
import com.fbs.admin.repository.ScheduleRepository;
import com.fbs.admin.service.AirlineService;
import com.fbs.admin.service.FlightScheduleService;
import com.fbs.admin.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.fbs.admin.util.DateUtility.convertToFbsFormat;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private FlightScheduleRepository flightScheduleRepository;
    private ScheduleRepository scheduleRepository;

    private AirlineService airlineService;
    private FlightService flightService;


    @Autowired
    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository, ScheduleRepository scheduleRepository, AirlineService airlineService, FlightService flightService) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.scheduleRepository = scheduleRepository;
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @Override
    public FlightSchedule addFlightSchedule(FlightSchedule flightSchedule, String startDateTime, String endDateTime) {
        if (flightSchedule.getAirLineCode() != null && flightSchedule.getFlightNumber() != null) {
            Optional<FlightSchedule> fs = flightScheduleRepository.findFlightSchedule(flightSchedule.getAirLineCode(), flightSchedule.getFlightNumber());
            if (fs.isPresent()) {
                throw new FBSException("Flight schedule is already available for this " + fs.get().getAirLineCode() + " and " + fs.get().getFlightNumber());
            }
            Optional<AirlineDTO> airline = (airlineService.findAirline(flightSchedule.getAirLineCode()));
            if (airline != null) {
                if (!airline.get().getStatus().toString().equalsIgnoreCase("true")) {
                    Flight flight = flightService.findFlight(flightSchedule.getFlightNumber());

                    if (flight.getFromLocation().equalsIgnoreCase(flight.getFromLocation())) {
                        flightSchedule.setFromLocation(flight.getFromLocation());
                    }
                    if (flight.getToLocation().equalsIgnoreCase(flight.getToLocation())) {
                        flightSchedule.setToLocation(flight.getToLocation());
                    }
                    if (startDateTime != null && endDateTime != null) {
                        flightSchedule.setStartDateTime((LocalDateTime) convertToFbsFormat(startDateTime));
                        flightSchedule.setEndDataTime((LocalDateTime) convertToFbsFormat(endDateTime));
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


        /*if (flightSchedule.getAirLineCode() != null && flightSchedule.getFlightNumber() != null) {
            FlightSchedule fs=flightScheduleRepository.findFlightSchedule(flightSchedule.getAirLineCode(),flightSchedule.getFlightNumber());
            if(fs!=null){
                throw new FBSException("Flight schedule is already available for this " + fs.getAirLineCode() + " and " + fs.getFlightNumber());
            }
            Schedule schedule = new Schedule();
            Airline airline = (airlineService.findAirline(flightSchedule.getAirLineCode()));
            if (airline != null) {
                if (!airline.getStatus().toString().equalsIgnoreCase("true")) {
                    Flight flight = flightService.findFlight(flightSchedule.getFlightNumber());
                    if (flight != null) {
                        if (flight.getFromLocation().equalsIgnoreCase(fromPlace)) {
                            schedule.setFromPlace(flight.getFromLocation());
                        } if (flight.getToLocation().equalsIgnoreCase(toPlace)) {
                            schedule.setToPlace(flight.getToLocation());
                        }
                    }

                    if (startDateTime != null && endDateTime != null) {
                        schedule.setStartDateTime((LocalDateTime) convertToFbsFormat(startDateTime));
                        schedule.setEndDateTime((LocalDateTime) convertToFbsFormat(endDateTime));
                    }
                    flightSchedule.setFlight(flight);
                    flightSchedule.setSchedule(schedule);
                    flightSchedule.setAvailableSeats(flight.getAvailableSeats());
                    return flightScheduleRepository.save(flightSchedule);
                } else {
                    throw new FBSException("Airline with this " + airline.getAirLineCode() + " code is Blocked, please take another airline");
                }
            } else {
                throw new FBSException("Airline with code : " + airline.getAirLineCode() + " not exists");
            }
        } else {
            throw new FBSException("Airline Code & Flight Number must not be null to schedule flight");
        }*/
    }

    @Override
    public FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule, String startDateTime, String endDateTime) {
        if (flightSchedule.getId() != null && flightSchedule.getAirLineCode() != null && flightSchedule.getFlightNumber() != null) {
            Optional<FlightSchedule> updateScheduleFlight = flightScheduleRepository.findById(flightSchedule.getId());
            if (updateScheduleFlight.isPresent()) {
                Optional<FlightSchedule> fs = flightScheduleRepository.findFlightSchedule(flightSchedule.getAirLineCode(), flightSchedule.getFlightNumber());
                if (fs.isPresent()) {
                    throw new FBSException("Flight schedule is already available for this " + fs.get().getAirLineCode() + " and " + fs.get().getFlightNumber());
                }
                Optional<AirlineDTO> airline = (airlineService.findAirline(flightSchedule.getAirLineCode()));
                if (airline != null) {
                    if (!airline.get().getStatus().toString().equalsIgnoreCase("true")) {
                        Flight flight = flightService.findFlight(flightSchedule.getFlightNumber());
                        if (startDateTime != null && endDateTime != null) {
                            flightSchedule.setStartDateTime((LocalDateTime) convertToFbsFormat(startDateTime));
                            flightSchedule.setEndDataTime((LocalDateTime) convertToFbsFormat(endDateTime));
                        }
                        flightSchedule.setFlight(flight);
                        return flightScheduleRepository.save(flightSchedule);
                    } else {
                        throw new FBSException("Airline with this " + airline.get().getAirLineCode() + " code is Blocked, please take another airline");
                    }
                } else {
                    throw new FBSException("Airline with code : " + airline.get().getAirLineCode() + " not exists");
                }
            } else {
                throw new FBSException("Scheduled Airline is not found with this " + flightSchedule.getId() + " id and " + flightSchedule.getFlightNumber() + "," + flightSchedule.getAirLineCode() + " Please schedule once again");
            }
        } else {
            throw new FBSException("Scheduled Id " + flightSchedule.getId() + " Flight Number " + flightSchedule.getFlightNumber() + " Airline Code " + flightSchedule.getAirLineCode());
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
    public FlightSchedule findFlightSchedule(Long flightId) throws FBSException {
        if (flightId == null) {
            throw new FBSException("Schedule Flight Id Required");
        }
        Optional<FlightSchedule> flightSchedule = flightScheduleRepository.findById(flightId);
        if (!flightSchedule.isPresent()) {
            throw new FBSException("Schedule Flight Id Required, Please Enter a valid Schedule Flight Id");
        } else {
            return flightSchedule.get();
        }
    }

    @Override
    public Iterable<FlightSchedule> findAllFlightSchedules() {
        return flightScheduleRepository.findAll();
    }
}

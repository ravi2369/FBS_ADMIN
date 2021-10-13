package com.fbs.admin.service.serviceImpl;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.Airline;
import com.fbs.admin.model.dto.AirlineDTO;
import com.fbs.admin.repository.AirlineRepository;
import com.fbs.admin.service.AirlineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline addAirline(AirlineDTO airlineDTO) throws FBSException {
        if (airlineDTO.getAirLineCode() != null) {
            Optional<Airline> findById = airlineRepository.findById(airlineDTO.getAirLineCode());
            if (!findById.isPresent()) {
                Airline airline = new Airline();
                BeanUtils.copyProperties(airlineDTO, airline, airlineDTO.getStatus());
                if (airlineDTO.getStatus() != null) {
                    if (airlineDTO.getStatus().toString().equalsIgnoreCase("Block")) {
                        airline.setStatus(true);
                    } else if (airlineDTO.getStatus().toString().equalsIgnoreCase("Unblock")) {
                        airline.setStatus(false);
                    }
                } else {
                    airline.setStatus(false);
                }
                return airlineRepository.save(airline);
            } else
                throw new FBSException("Airline with code : " + airlineDTO.getAirLineCode() + " already present");
        } else {
            throw new FBSException("Airline Code must not be empty");
        }

    }

    @Override
    public Airline updateAirline(AirlineDTO airlineDTO) throws FBSException {
        if (airlineDTO.getAirLineCode() != null) {
            Optional<Airline> findById = airlineRepository.findById(airlineDTO.getAirLineCode());
            if (findById.isPresent()) {
                Airline airline = new Airline();
                BeanUtils.copyProperties(airlineDTO, airline, airlineDTO.getStatus());
                if (airlineDTO.getStatus() != null) {
                    if (airlineDTO.getStatus().toString().equalsIgnoreCase("Block")) {
                        airline.setStatus(true);
                    } else if (airlineDTO.getStatus().toString().equalsIgnoreCase("Unblock")) {
                        airline.setStatus(false);
                    }
                }
                return airlineRepository.save(airline);
            } else
                throw new FBSException("Airline with code : " + airlineDTO.getAirLineCode() + " not exists");
        } else {
            throw new FBSException("Airline Code must not be empty");
        }
    }

    @Override
    public Optional<AirlineDTO> findAirline(String airLineCode) throws FBSException {
        if (airLineCode != null && !airLineCode.isEmpty()) {
            Optional<Airline> airline = airlineRepository.findById(airLineCode);
            if (airline.isPresent()) {
                Optional<AirlineDTO> airlineDTO = Optional.of(new AirlineDTO());
                airlineDTO.get().setAirLineCode(airline.get().getAirLineCode());
                airlineDTO.get().setAirLineName(airline.get().getAirLineName());
                if (airline.get().getStatus() != null) {
                    if (airline.get().getStatus().toString().equalsIgnoreCase("true")) {
                        airlineDTO.get().setStatus("Block");
                    } else if (airline.get().getStatus().toString().equalsIgnoreCase("false")) {
                        airlineDTO.get().setStatus("Unblock");
                    }
                }
                return airlineDTO;
            } else
                throw new FBSException("Airline with airLineCode code: " + airLineCode + " not exists");
        } else {
            throw new FBSException("Airline Code must not be empty");
        }
    }

    @Override
    public List<AirlineDTO> findAllAirlines() {
        Iterable<Airline> airlines = airlineRepository.findAll();
        List<AirlineDTO> airlineDTOS = new ArrayList<>();
        for (Airline airline : airlines) {
            AirlineDTO airlineDTO = new AirlineDTO();
            if (airline.getStatus() != null) {
                BeanUtils.copyProperties(airline, airlineDTO, airline.getStatus().toString());
                if (airline.getStatus() != null) {
                    if (airline.getStatus().toString().equalsIgnoreCase("true")) {
                        airlineDTO.setStatus("Block");
                    } else if (airline.getStatus().toString().equalsIgnoreCase("false")) {
                        airlineDTO.setStatus("Unblock");
                    }
                }
            } else {
                BeanUtils.copyProperties(airline, airlineDTO);
            }
            airlineDTOS.add(airlineDTO);
        }
        return airlineDTOS;
    }

    @Override
    public String deleteAirline(String airLineCode) throws FBSException {
        if (airLineCode != null && !airLineCode.isEmpty()) {
            Optional<Airline> findById = airlineRepository.findById(airLineCode);
            if (findById.isPresent()) {
                airlineRepository.deleteById(airLineCode);
                return "Airline removed";
            } else
                throw new FBSException("Airline with code : " + airLineCode + " not exists");
        } else {
            throw new FBSException("Airline Code must not be empty");
        }
    }
}

package com.fbs.admin.repository;

import com.fbs.admin.exceptions.FBSException;
import com.fbs.admin.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.airLineCode = ?1 and FS.flightNumber = ?2")
    Optional<FlightSchedule> findFlightSchedule(String airLineCode, String flightNumber) throws FBSException;

    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.fromLocation LIKE ?1% and FS.toLocation LIKE ?2%")
    List<FlightSchedule> findScheduledFlights(String fromLocation, String toLocation) throws FBSException;

}

package com.fbs.admin.repository;

import com.fbs.admin.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.airLineCode = ?1 and FS.flightNumber = ?2")
    Optional<FlightSchedule> findFlightSchedule(String airLineCode, String flightNumber);

    @Query("SELECT FS FROM FlightSchedule FS WHERE FS.fromLocation= ?1 or FS.toLocation= ?2 or FS.startDateTime= ?3")
    Optional<FlightSchedule> findScheduledFlights(String fromLocation, String toLocation, LocalDateTime startDateTime);
}

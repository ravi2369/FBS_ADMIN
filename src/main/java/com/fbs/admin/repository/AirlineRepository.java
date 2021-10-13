package com.fbs.admin.repository;

import com.fbs.admin.model.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, String> {
}

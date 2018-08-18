package com.quantum.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantum.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}

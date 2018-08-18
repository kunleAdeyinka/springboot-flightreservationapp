package com.quantum.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantum.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

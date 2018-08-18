package com.quantum.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantum.flightreservation.dto.ReservationUpdateRequest;
import com.quantum.flightreservation.entities.Reservation;
import com.quantum.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation" + " for Id " + id);
		Optional<Reservation> reservationObj = reservationRepo.findById(id);
		Reservation reservation = reservationObj.get();
		return reservation;
	}
	
	@CrossOrigin
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation for " + request);
		Optional<Reservation> reservationObj = reservationRepo.findById(request.getId());
		Reservation reservation = reservationObj.get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
		Reservation updtReservation = reservationRepo.save(reservation);
		return updtReservation;
	}
}

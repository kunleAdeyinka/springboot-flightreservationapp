package com.quantum.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantum.flightreservation.dto.ReservationRequest;
import com.quantum.flightreservation.entities.Flight;
import com.quantum.flightreservation.entities.Reservation;
import com.quantum.flightreservation.repos.FlightRepository;
import com.quantum.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId")Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation " +  flightId);
		Optional<Flight> flight = flightRepository.findById(flightId);
		modelMap.addAttribute("flight", flight.get());
		LOGGER.info("Inside showCompleteReservation flight" +  flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation",method=RequestMethod.POST)
	public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap) {
		LOGGER.info("Inside completeReservation " +  reservationRequest);
		Reservation bookFlight = reservationService.bookFlight(reservationRequest);
		LOGGER.info("Inside completeReservation bookFlight" +  bookFlight);
		modelMap.addAttribute("message", "Reservation created successfully and the id is " + bookFlight.getId());
		return "reservationConfirmation";
	}
}

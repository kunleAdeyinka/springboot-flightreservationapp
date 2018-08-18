package com.quantum.flightreservation.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quantum.flightreservation.dto.ReservationRequest;
import com.quantum.flightreservation.entities.Flight;
import com.quantum.flightreservation.entities.Passenger;
import com.quantum.flightreservation.entities.Reservation;
import com.quantum.flightreservation.repos.FlightRepository;
import com.quantum.flightreservation.repos.PassengerRepository;
import com.quantum.flightreservation.repos.ReservationRepository;
import com.quantum.flightreservation.util.EmailUtil;
import com.quantum.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.quantum.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;	
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	ReservationRepository reservationRepo;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		LOGGER.info("Inside bookFlight");
		// Make payment to payment service - request.getCardNumber();
		Long flightId = request.getFlightId();
		Optional<Flight> optFlight = flightRepo.findById(flightId);
		Flight flight = optFlight.get();
		LOGGER.info("Fetching flight information " + flight);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving passenger information " + passenger);
		Passenger savedPassenger = passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setPassenger(savedPassenger);
		reservation.setFlight(flight);
		reservation.setCheckedIn(false);
		LOGGER.info("Saving reservation information " + reservation);		
		Reservation savedReservation = reservationRepo.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId()+ ".pdf";
		LOGGER.info("Generating the flight itenaray");		
		pdfGenerator.generateItinerary(savedReservation, filePath);
		LOGGER.info("Emailing the flight itenaray");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}

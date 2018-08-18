package com.quantum.flightreservation.services;

import com.quantum.flightreservation.dto.ReservationRequest;
import com.quantum.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);

}

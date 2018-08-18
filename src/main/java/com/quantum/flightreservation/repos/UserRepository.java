package com.quantum.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantum.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}

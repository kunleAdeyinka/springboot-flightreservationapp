package com.quantum.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantum.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}

package com.oneHealth.Hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.oneHealth.Hospital.entites.Hospital;

public interface HospitalService {
	
	/**
	 * Add a new Hospital.
	 * 
	 * @param c The Hospital to be added.
	 */
	void addHospital(Hospital c);
	
	/**
	 * Get all Hospitals.
	 * 
	 * @return The list of Hospitals.
	 */
	List<Hospital> getHospitals();
	
	/**
	 * Delete a Hospital by ID.
	 * 
	 * @param Hospital_Id The ID of the Hospital to be deleted.
	 */
	void delete(long Hospital_Id);
	
	/**
	 * Get a Hospital by ID.
	 * 
	 * @param Hospital_Id The ID of the Hospital to retrieve.
	 * @return An optional containing the Hospital, or an empty optional if not found.
	 */
	Optional<Hospital> getHospital(long Hospital_Id);
	
	/**
	 * Get Hospitals by email.
	 * 
	 * @param email_id The email of the Hospital.
	 * @return A list of Hospitals with the specified email, or an empty list if not found.
	 */
	List<Hospital> getHospitalbyEmail(String email_id);
	
	/**
	 * Update the details of a Hospital.
	 * 
	 * @param hospital The updated Hospital object.
	 * @param hid The ID of the Hospital to be updated.
	 */
	void update(Hospital hospital, long hid);
}

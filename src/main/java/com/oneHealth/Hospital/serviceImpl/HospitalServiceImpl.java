package com.oneHealth.Hospital.serviceImpl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oneHealth.Hospital.entites.Hospital;
import com.oneHealth.Hospital.repository.HospitalRepository;
import com.oneHealth.Hospital.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
    HospitalRepository repository;
	
	/**
	 * Add a new Hospital.
	 * 
	 * @param Hospital The Hospital to be added.
	 */
	@Override
	public void addHospital(Hospital Hospital) {
		repository.save(Hospital); // Save the Hospital object to the repository
	}
	
	/**
	 * Get all Hospitals.
	 * 
	 * @return The list of Hospitals.
	 */
	@Override
	public List<Hospital> getHospitals() {
		return repository.findAll(); // Retrieve all Hospitals from the repository
	}
	
	/**
	 * Delete a Hospital by ID.
	 * 
	 * @param Hospital_Id The ID of the Hospital to be deleted.
	 */
	@Override
	public void delete(long Hospital_Id) {
		repository.deleteById(Hospital_Id); // Delete the Hospital from the repository based on ID
	}
	
	/**
	 * Get a Hospital by ID.
	 * 
	 * @param Hospital_Id The ID of the Hospital to retrieve.
	 * @return An optional containing the Hospital, or an empty optional if not found.
	 */
	@Override
	public Optional<Hospital> getHospital(long Hospital_Id) {
		return repository.findById(Hospital_Id); // Retrieve the Hospital from the repository based on ID
	}
	
	/**
	 * Get Hospitals by email.
	 * 
	 * @param email_id The email ID of the Hospital.
	 * @return The list of Hospitals matching the email ID.
	 */
	@Override
	public List<Hospital> getHospitalbyEmail(String email_id) {
		return repository.findByEmail(email_id); // Retrieve Hospitals with the given email ID from the repository
	}

	/**
	 * Update the details of a Hospital.
	 * 
	 * @param hospital The updated Hospital object.
	 * @param hid The ID of the Hospital to be updated.
	 */
	@Override
	public void update(Hospital hospital, long hid) {
		System.out.println("inside update");
		repository.update(
				hospital.getHospitalName(),
				hospital.getHospitalCity(),
				hospital.getHospitalAddress(),
				hospital.gethOpenTime(),
				hospital.gethCloseTime(),
				hospital.gethOfficialEmail(),
				hospital.gethOfficialContact(),
				hospital.gethRegCertiPath(),
				hospital.gethGstNumber(),
				hospital.gethRegTimeStamp(),
				hid
		);
	}
}

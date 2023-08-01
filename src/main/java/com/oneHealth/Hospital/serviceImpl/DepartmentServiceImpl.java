package com.oneHealth.Hospital.serviceImpl;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.oneHealth.Hospital.entites.Department;
import com.oneHealth.Hospital.repository.DepartmentRepository;
import com.oneHealth.Hospital.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository repository;

	/**
	 * Add a new Department.
	 * 
	 * @param Department The Department to be added.
	 */
	@Override
	public void addDepartment(Department Department) {
		repository.save(Department); // Save the Department object to the repository
	}
	
	/**
	 * Get all Departments.
	 * 
	 * @return The list of Departments.
	 */
	@Override
	public List<Department> getDepartments() {
		return repository.findAll(); // Retrieve all Departments from the repository
	}
	
	/**
	 * Delete a Department by ID.
	 * 
	 * @param Department_Id The ID of the Department to be deleted.
	 */
	@Override
	public void delete(int Department_Id) {
		repository.deleteById(Department_Id); // Delete the Department from the repository based on ID
	}
	
	/**
	 * Get a Department by ID.
	 * 
	 * @param Department_Id The ID of the Department to retrieve.
	 * @return An optional containing the Department, or an empty optional if not found.
	 */
	@Override
	public Optional<Department> getDepartment(int Department_Id) {
		return repository.findById(Department_Id); // Retrieve the Department from the repository based on ID
	}
	
	/**
	 * Update the details of a Department.
	 * 
	 * @param Department The updated Department object.
	 * @param did The ID of the Department to be updated.
	 */
	@Override
	public void update(Department Department, int did) {
		System.out.println("inside update");
		repository.update(
				Department.getDeptName(),
				Department.getDeptDesc(),
				Department.getParentDeptId(),
				Department.getFlag(),
				Department.getDeptImgPath(),
				Department.getHospitalId(),
				did
		);
	}
}

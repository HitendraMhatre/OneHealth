package com.oneHealth.Hospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.oneHealth.Hospital.entites.Department;

public interface DepartmentService {
	
	/**
	 * Add a new Department.
	 * 
	 * @param c The Department to be added.
	 */
	void addDepartment(Department c);
	
	/**
	 * Get all Departments.
	 * 
	 * @return The list of Departments.
	 */
	List<Department> getDepartments();
	
	/**
	 * Delete a Department by ID.
	 * 
	 * @param Department_Id The ID of the Department to be deleted.
	 */
	void delete(int Department_Id);
	
	/**
	 * Get a Department by ID.
	 * 
	 * @param Department_Id The ID of the Department to retrieve.
	 * @return An optional containing the Department, or an empty optional if not found.
	 */
	Optional<Department> getDepartment(int Department_Id);
	
	/**
	 * Update the details of a Department.
	 * 
	 * @param Department The updated Department object.
	 * @param hid The ID of the Department to be updated.
	 */
	void update(Department Department, int hid);
}

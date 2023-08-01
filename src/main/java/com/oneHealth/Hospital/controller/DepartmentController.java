package com.oneHealth.Hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.Hospital.entites.Department;
import com.oneHealth.Hospital.exception.DepartmentNotFoundException;
import com.oneHealth.Hospital.service.DepartmentService;

/**
 * The DepartmentController class handles the API endpoints related to Department operations.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
public class DepartmentController {

    @Autowired
    DepartmentService service;

    /**
     * Retrieves all the Departments.
     *
     * @return ResponseEntity with the list of Department objects if they exist,
     *         or a no content response if no Departments are found.
     */
    @GetMapping(value = "api/Departments/")
    public ResponseEntity<List<Department>> showDepartments() {
        List<Department> departments = service.getDepartments();
        if (departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(departments);
        }
    }

    /**
     * Retrieves a specific Department by its ID.
     *
     * @param deptId The ID of the Department to retrieve.
     * @return ResponseEntity with the Department if found,
     *         or throws DepartmentNotFoundException if the Department is not found.
     */
    @GetMapping(value = "api/DepartmentById/{deptId}")
    public ResponseEntity<Department> getDepartment(@PathVariable int deptId) {
        try {
            Optional<Department> department = service.getDepartment(deptId);
            return department.map(ResponseEntity::ok).orElseThrow(() ->
                    new DepartmentNotFoundException("Department not found with ID: " + deptId));
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Removes a Department by its ID.
     *
     * @param deptId The ID of the Department to remove.
     * @return ResponseEntity with a success message if the Department is deleted successfully,
     *         or an error message if the Department deletion fails.
     */
    @DeleteMapping(value = "api/Department/{deptId}")
    public ResponseEntity<String> removeDepartment(@PathVariable int deptId) {
        try {
            service.delete(deptId);
            return ResponseEntity.ok("Department deleted successfully.");
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Department not found with ID: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Department: " + e.getMessage());
        }
    }

    /**
     * Adds a new Department.
     *
     * @param department The Department object to add.
     * @return ResponseEntity with a success message if the Department is added successfully,
     *         or an error message if the Department addition fails.
     */
    @PostMapping(value = "api/Department")
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        try {
            service.addDepartment(department);
            return ResponseEntity.ok("Department added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Department: " + e.getMessage());
        }
    }

    /**
     * Updates an existing Department by its ID.
     *
     * @param department The updated Department object.
     * @param deptId     The ID of the Department to update.
     * @return ResponseEntity with a success message if the Department is updated successfully,
     *         or an error message if the Department update fails.
     */
    @PutMapping(value = "api/Department/{deptId}")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department, @PathVariable int deptId) {
        try {
            service.update(department, deptId);
            return ResponseEntity.ok("Department details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify Department details: " + e.getMessage());
        }
    }
}


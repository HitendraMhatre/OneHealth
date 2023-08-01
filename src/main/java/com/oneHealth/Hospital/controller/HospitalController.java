package com.oneHealth.Hospital.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.oneHealth.Hospital.entites.Hospital;
import com.oneHealth.Hospital.exception.HospitalNotFoundException;
import com.oneHealth.Hospital.service.HospitalService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * The HospitalController class handles the API endpoints related to Hospital operations.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
public class HospitalController {

    public static int randomNumber;

    @Autowired
    HospitalService service;

    /**
     * Retrieves all the Hospitals.
     *
     * @return ResponseEntity with the list of Hospital objects if they exist,
     *         or a no content response if no Hospitals are found.
     */
    @GetMapping(value = "api/Hospitals/")
    public ResponseEntity<List<Hospital>> showHospitals() {
        List<Hospital> hospitals = service.getHospitals();
        if (hospitals.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(hospitals);
        }
    }

    /**
     * Retrieves a list of Hospitals by their email address.
     *
     * @param email_id The email address of the Hospitals to retrieve.
     * @return List of Hospital objects that match the given email.
     */
    @GetMapping(value = "api/getbyemail/{email_id}")
    public List<Hospital> getHospitalByEmail(@PathVariable String email_id) {
        System.out.println("inside hospital by email");
        System.out.println(service.getHospitalbyEmail(email_id));
        return service.getHospitalbyEmail(email_id);
    }

    /**
     * Retrieves a specific Hospital by their ID.
     *
     * @param hospital_Id The ID of the Hospital to retrieve.
     * @return ResponseEntity with the Hospital if found,
     *         or throws HospitalNotFoundException if the Hospital is not found.
     */
    @GetMapping(value = "api/HospitalById/{hospital_Id}")
    public ResponseEntity<Hospital> getHospital(@PathVariable int hospital_Id) {
        try {
            Optional<Hospital> hospital = service.getHospital(hospital_Id);
            return hospital.map(ResponseEntity::ok).orElseThrow(() ->
                    new HospitalNotFoundException("Hospital not found with ID: " + hospital_Id));
        } catch (HospitalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Removes a Hospital by their ID.
     *
     * @param hospital_Id The ID of the Hospital to remove.
     * @return ResponseEntity with a success message if the Hospital is deleted successfully,
     *         or an error message if the Hospital deletion fails.
     */
    @DeleteMapping(value = "api/Hospital/{hospital_Id}")
    public ResponseEntity<String> removeHospital(@PathVariable int hospital_Id) {
        try {
            service.delete(hospital_Id);
            return ResponseEntity.ok("Hospital deleted successfully.");
        } catch (HospitalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Hospital not found with ID: " + hospital_Id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Hospital: " + e.getMessage());
        }
    }

    /**
     * Adds a new Hospital.
     *
     * @param hospital The Hospital object to add.
     * @return ResponseEntity with a success message if the Hospital is added successfully,
     *         or an error message if the Hospital addition fails.
     */
    @PostMapping(value = "api/Hospital")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital) {
        try {
            // Set the registration timestamp to the current time
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            hospital.sethRegTimeStamp(currentTimestamp);

            // Call the service method to add the hospital
            service.addHospital(hospital);
            return ResponseEntity.ok("Hospital added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Hospital: " + e.getMessage());
        }
    }

    /**
     * Updates an existing Hospital by its ID.
     *
     * @param hospital The updated Hospital object.
     * @param hid      The ID of the Hospital to update.
     * @return ResponseEntity with a success message if the Hospital is updated successfully,
     *         or an error message if the Hospital update fails.
     */
    @PutMapping(value = "api/Hospital/{hid}")
    public ResponseEntity<String> updateHospital(@RequestBody Hospital hospital, @PathVariable int hid) {
        try {
            System.out.println("inside updateHospital of controller");
            service.update(hospital, hid);
            return ResponseEntity.ok("Hospital details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify Hospital details: " + e.getMessage());
        }
    }

}


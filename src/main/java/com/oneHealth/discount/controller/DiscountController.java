package com.oneHealth.discount.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oneHealth.discount.entities.Discount;
import com.oneHealth.discount.exception.DiscountNotFoundException;
import com.oneHealth.discount.service.DiscountService;

/**
 * The DiscountController class handles the API endpoints related to Discount operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/discount/")
public class DiscountController {

    @Autowired
    DiscountService service;

    /**
     * Retrieves all the Discounts.
     *
     * @return ResponseEntity with the list of Discount objects if they exist,
     *         or a no content response if no Discounts are found.
     */
    @GetMapping(value = "api/Discount")
    public ResponseEntity<List<Discount>> showDiscounts() {
        try {
            List<Discount> discounts = service.getDiscounts();
            
            for (Discount discount : discounts) {
                discount.calculateActivationStatus();
            }
            
            if (discounts.isEmpty()) {
                // If no discounts are found, return an empty list with status 200 (OK)
                return ResponseEntity.ok(Collections.emptyList());
            } else {
                return ResponseEntity.ok(discounts);
            }
        } catch (Exception e) {
            // Handle other exceptions with status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    /**
     * Retrieves a specific Discount by its ID.
     *
     * @param deptId The ID of the Discount to retrieve.
     * @return ResponseEntity with the Discount if found,
     *         or throws DiscountNotFoundException if the Discount is not found.
     */
    @GetMapping(value = "api/DiscountById/{offerId}")
    public ResponseEntity<Discount> getDiscount(@PathVariable int offerId) {
        try {
            Optional<Discount> optionalDiscount = service.getDiscount(offerId);
            
            if (optionalDiscount.isPresent()) {
                Discount discount = optionalDiscount.get();
                discount.calculateActivationStatus();
                return ResponseEntity.ok(discount);
            } else {
                // If the discount is not found, return a response with status 404 (Not Found)
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle other exceptions with status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    /**
     * Retrieves a specific Discount by its code.
     *
     * @param offerCode The code of the Discount to retrieve.
     * @return ResponseEntity with the Discount if found,
     *         or throws DiscountNotFoundException if the Discount is not found.
     */
    @GetMapping(value = "api/DiscountByOfferCode/{offerCode}")
    public ResponseEntity<Discount> getDiscountByCode(@PathVariable String offerCode) {
        try {
            Optional<Discount> optionalDiscount = service.getDiscountByCode(offerCode);

            if (optionalDiscount.isPresent()) {
                Discount discount = optionalDiscount.get();
                discount.calculateActivationStatus();
                return ResponseEntity.ok(discount);
            } else {
                // If the discount is not found, return a response with status 404 (Not Found)
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle other exceptions with status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    /**
     * Retrieves a specific Discount by its type and typeId.
     *
     * @param deptId The ID of the Discount to retrieve.
     * @return ResponseEntity with the Discount if found,
     *         or throws DiscountNotFoundException if the Discount is not found.
     */
    @GetMapping(value = "api/DiscountByTypeAndTypeId/{type}/{typeId}")
    public ResponseEntity<List<Discount>> getDiscountBytypeId(@PathVariable String type, @PathVariable int typeId) {
        try {
            List<Discount> discounts = service.getDiscountBytypeId(type, typeId);
            
            for (Discount discount : discounts) {
                discount.calculateActivationStatus();
            }
            
            if (discounts.isEmpty()) {
                // If no discounts are found, return an empty list with status 200 (OK)
                return ResponseEntity.ok(Collections.emptyList());
            } else {
                return ResponseEntity.ok(discounts);
            }
        } catch (Exception e) {
            // Handle other exceptions with status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Retrieves a specific Discount by its type.
     *
     * @param type The code of the Discount to retrieve.
     * @return ResponseEntity with the Discount if found,
     *         or throws DiscountNotFoundException if the Discount is not found.
     */
    @GetMapping(value = "api/DiscountByType/{type}")
    public ResponseEntity<List<Discount>> getDiscountByType(@PathVariable String type) {
        try {
            List<Discount> discounts = service.getDiscountByType(type);
            
            for (Discount discount : discounts) {
                discount.calculateActivationStatus();
            }
            
            if (discounts.isEmpty()) {
                // If no discounts are found, return an empty list with status 200 (OK)
                return ResponseEntity.ok(Collections.emptyList());
            } else {
                return ResponseEntity.ok(discounts);
            }
        } catch (Exception e) {
            // Handle other exceptions with status 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


   
    /**
     * Removes a Discount by its ID.
     *
     * @param deptId The ID of the Discount to remove.
     * @return ResponseEntity with a success message if the Discount is deleted successfully,
     *         or an error message if the Discount deletion fails.
     */
    @DeleteMapping(value = "api/Discount/{offerId}")
    public ResponseEntity<String> removeDiscount(@PathVariable int offerId) {
        try {
            service.delete(offerId);
            return ResponseEntity.ok("Discount deleted successfully.");
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Discount not found with ID: " + offerId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Discount: " + e.getMessage());
        }
    }

    /**
     * Adds a new Discount.
     *
     * @param Discount The Discount object to add.
     * @return ResponseEntity with a success message if the Discount is added successfully,
     *         or an error message if the Discount addition fails.
     */
    @PostMapping(value = "api/Discount")
    public ResponseEntity<String> addDiscount(@RequestBody Discount Discount) {
        try {
        	// Set the registration timestamp to the current time
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            Discount.setCreatedTimeStamp(currentTimestamp);
            Discount.calculateActivationStatus();
            service.addDiscount(Discount);
            return ResponseEntity.ok("Discount added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add Discount: " + e.getMessage());
        }
    }

    /**
     * Updates an existing Discount by its ID.
     *
     * @param Discount The updated Discount object.
     * @param deptId     The ID of the Discount to update.
     * @return ResponseEntity with a success message if the Discount is updated successfully,
     *         or an error message if the Discount update fails.
     */
    @PutMapping(value = "api/Discount/{deptId}")
    public ResponseEntity<String> updateDiscount(@RequestBody Discount Discount, @PathVariable int deptId) {
        try {
            service.update(Discount, deptId);
            Discount.calculateActivationStatus();
            return ResponseEntity.ok("Discount details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify Discount details: " + e.getMessage());
        }
    }

}


package com.oneHealth.review.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.oneHealth.review.dto.Hospital;
import com.oneHealth.review.dto.LabManagement;
import com.oneHealth.review.dto.Pharmacy;
import com.oneHealth.review.entities.Review;
import com.oneHealth.review.exception.ResourceNotFoundException;
import com.oneHealth.review.repository.ReviewRepository;
import com.oneHealth.review.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository repository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	/**
	 * Add a new Review.
	 * 
	 * @param review The Review to be added.
	 * @throws ResourceNotFoundException if the resource is not found.
	 */
	@Override
	public void addReview(Review review) throws ResourceNotFoundException {
		
		if("Pharmacy".equalsIgnoreCase(review.getType())) {
			Pharmacy pharmacyDto = webClientBuilder.build()
		            .get()
		            .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/{type_id}",review.getTypeId())
		            .retrieve()
		            .bodyToMono(Pharmacy.class)
		            .block();
		    System.out.println(pharmacyDto);
		    if (pharmacyDto == null) {
		        throw new ResourceNotFoundException("Pharmacy Not found with pharma id : " + review.getTypeId());
		    }
		}
		else if("Lab".equalsIgnoreCase(review.getType())) {
			LabManagement labDto = webClientBuilder.build()
		            .get()
		            .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/labs/{type_id}",review.getTypeId())
		            .retrieve()
		            .bodyToMono(LabManagement.class)
		            .block();
		    System.out.println(labDto);
		    if (labDto == null) {
		        throw new ResourceNotFoundException("Lab Not found with Lab id : " + review.getTypeId());
		    }
		}
		else if("Hospital".equalsIgnoreCase(review.getType())) {
			Hospital hospitalDto = webClientBuilder.build()
		            .get()
		            .uri("https://hospitalservice-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/hservice/api/HospitalById/{type_id}",review.getTypeId())
		            .retrieve()
		            .bodyToMono(Hospital.class)
		            .block();
		    System.out.println(hospitalDto);
		    if (hospitalDto == null) {
		        throw new ResourceNotFoundException("Hospital Not found with Hospital id : " + review.getTypeId());
		    }
		}
		else if("Doctor".equalsIgnoreCase(review.getType())) {
			Object doctorDto = webClientBuilder.build()
		            .get()
		            .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/doctors/addressprofileregistration/getdoctorprofile/{type_id}",review.getTypeId())
		            .retrieve()
		            .bodyToMono(Object.class) // Replace Object with the actual type if known
		            .block();
		    System.out.println(doctorDto);
		    if (doctorDto == null) {
		        throw new ResourceNotFoundException("Doctor Not found with Doctor id : " + review.getTypeId());
		    }
		}
		else if("oneHealth".equalsIgnoreCase(review.getType())) {
			System.out.println("Review for oneHealth");
			// Handle oneHealth specific logic here
		}
		else {
			throw new ResourceNotFoundException("Not found type : " + review.getType());
		}
		
		review.setEditedTime(new Timestamp(System.currentTimeMillis())); // Set the edited time
		repository.save(review); // Save the Review object to the repository
	}

	/**
	 * Get all Reviews.
	 * 
	 * @return The list of Reviews.
	 */
	@Override
	public List<Review> getReviews() {
		return repository.findAll(); // Retrieve all Reviews from the repository
	}

	/**
	 * Delete a Review by ID.
	 * 
	 * @param reviewId The ID of the Review to be deleted.
	 */
	@Override
	public void delete(int reviewId) {
		repository.deleteById(reviewId); // Delete the Review from the repository based on ID
	}

	/**
	 * Get a Review by ID.
	 * 
	 * @param reviewId The ID of the Review to retrieve.
	 * @return An optional containing the Review, or an empty optional if not
	 *         found.
	 */
	@Override
	public Optional<Review> getReview(int reviewId) {
		return repository.findById(reviewId); // Retrieve the Review from the repository based on ID
	}
	
	/**
	 * Update the details of a Review.
	 * 
	 * @param review The updated Review object.
	 * @param reviewId The ID of the Review to be updated.
	 */
	@Override
	public void update(Review review, int reviewId)  {
		review.setEditedTime(new Timestamp(System.currentTimeMillis())); // Set the edited time
		repository.update(
			review.getTypeId(),
			review.getUserId(),
			review.getType(),
			review.getBody(),
			review.getRating(),
			review.getEditedTime(),
			reviewId
		);
	}

	@Override
	public List<Review> getReviewBytypeId(String type, int typeId) {
		return repository.findByTypeAndTypeId(type, typeId);
	}

	@Override
	public List<Review> getReviewByType(String type) {
		return repository.findAllBytype(type);
	}

	@Override
	public Double getAvgRatingByID(String type, int typeId) {
		return repository.findAvgRatingByID(type, typeId);
	}
	
	@Override
	public Review getReviewByUserAndType(int userId, String type, int typeId) {
        return repository.findByUserIdAndTypeAndTypeId(userId, type, typeId);
    }

	@Override
	public List<Review> getReviewByUserId(int userId) {
		return repository.findAllByuserId(userId);
	}
}

package com.oneHealth.discount.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.oneHealth.discount.dto.Pharmacy;
import com.oneHealth.discount.dto.LabManagement;
import com.oneHealth.discount.dto.Hospital;
import com.oneHealth.discount.entities.Discount;
import com.oneHealth.discount.exception.DiscountNotFoundException;
import com.oneHealth.discount.exception.ResourceNotFoundException;
import com.oneHealth.discount.repository.DiscountRepository;
import com.oneHealth.discount.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	DiscountRepository repository;
	
	 @Autowired
	 private ModelMapper mapper;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	/**
	 * Add a new Discount.
	 * 
	 * @param Discount The Discount to be added.
	 */
	@Override
	public void addDiscount(Discount Discount) throws ResourceNotFoundException {
		
		if("Pharmacy".equalsIgnoreCase(Discount.getType())) {
		Pharmacy pharmacyDto = webClientBuilder.build()
	            .get()
	            .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/{type_id}",Discount.getTypeId())
	            .retrieve()
	            .bodyToMono(Pharmacy.class)
	            .block();
	    System.out.println(pharmacyDto);
	    if (pharmacyDto == null) {
//	        logger.warning("Failed to add test to lab cart: Patient not found with ID " + itemRequest.getPatient_id());
	        throw new ResourceNotFoundException("Pharmacy Not found with pharma id : " + Discount.getTypeId());
	    }
		}
		else if("Lab".equalsIgnoreCase(Discount.getType())) {
			LabManagement labDto = webClientBuilder.build()
		            .get()
		            .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/labs/{type_id}",Discount.getTypeId())
		            .retrieve()
		            .bodyToMono(LabManagement.class)
		            .block();
		    System.out.println(labDto);
		    if (labDto == null) {
//		        logger.warning("Failed to add test to lab cart: Patient not found with ID " + itemRequest.getPatient_id());
		        throw new ResourceNotFoundException("Lab Not found with Lab id : " + Discount.getTypeId());
		    }
		}
		
		else if("Hospital".equalsIgnoreCase(Discount.getType())) {
			Hospital HospitalDto = webClientBuilder.build()
		            .get()
		            .uri("https://hospitalservice-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/hservice/api/HospitalById/{type_id}",Discount.getTypeId())
		            .retrieve()
		            .bodyToMono(Hospital.class)
		            .block();
		    System.out.println(HospitalDto);
		    if (HospitalDto == null) {
//		        logger.warning("Failed to add test to lab cart: Patient not found with ID " + itemRequest.getPatient_id());
		        throw new ResourceNotFoundException("Hospital Not found with Hospital id : " + Discount.getTypeId());
		    }
		}
		
		System.out.println("Hi");
		repository.save(Discount); // Save the Discount object to the repository
	}

	/**
	 * Get all Discounts.
	 * 
	 * @return The list of Discounts.
	 */
	@Override
	public List<Discount> getDiscounts() {
		return repository.findAll(); // Retrieve all Discounts from the repository
	}

	/**
	 * Delete a Discount by ID.
	 * 
	 * @param Discount_Id The ID of the Discount to be deleted.
	 */
	@Override
	public void delete(int Discount_Id) {
		repository.deleteById(Discount_Id); // Delete the Discount from the repository based on ID
	}

	/**
	 * Get a Discount by ID.
	 * 
	 * @param Discount_Id The ID of the Discount to retrieve.
	 * @return An optional containing the Discount, or an empty optional if not
	 *         found.
	 */
	@Override
	public Optional<Discount> getDiscount(int Discount_Id) {
		return repository.findById(Discount_Id); // Retrieve the Discount from the repository based on ID
	}
	
	/**
	 * Update the details of a BlogPost.
	 * 
	 * @param BlogPost The updated BlogPost object.
	 * @param did The ID of the BlogPost to be updated.
	 */
	@Override
	public void update(Discount Discount, int offerId)  {
		System.out.println("inside update");
		repository.update(
				Discount.getTypeId(),
				Discount.getUserId(),
				Discount.getType(),
				Discount.getOfferName(),
				Discount.getOfferCode(),
				Discount.getOfferDescription(),
				Discount.getDiscountPercentage(),
				Discount.getStartDate(),
//				Discount.getCreatedTimeStamp(),
				Discount.getEndDate(),
				Discount.getCriteria(),
				offerId
		);
		
	}

	@Override
	public List<Discount> getDiscountBytypeId(String type, int typeId) {
		return repository.findByTypeAndTypeId(type,typeId);
	}

	@Override
	public Optional<Discount> getDiscountByCode(String offerCode) {
		return repository.findByofferCode(offerCode);
	}

	@Override
	public List<Discount> getDiscountByType(String type) {
		// TODO Auto-generated method stub
		return repository.findAllBytype(type);
	}

}

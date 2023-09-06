package com.oneHealth.discount.service;

import java.util.List;
import java.util.Optional;

import com.oneHealth.discount.entities.Discount;
import com.oneHealth.discount.exception.ResourceNotFoundException;


public interface DiscountService {
	
	/**
	 * Add a new Discount.
	 * 
	 * @param c The Discount to be added.
	 * @throws ResourceNotFoundException 
	 */
	void addDiscount(Discount c) throws ResourceNotFoundException;
	
	/**
	 * Get all Discounts.
	 * 
	 * @return The list of Discounts.
	 */
	List<Discount> getDiscounts();
	
	/**
	 * Delete a Discount by ID.
	 * 
	 * @param Discount_Id The ID of the Discount to be deleted.
	 */
	void delete(int Discount_Id);
	
	/**
	 * Get a Discount by ID.
	 * 
	 * @param Discount_Id The ID of the Discount to retrieve.
	 * @return An optional containing the Discount, or an empty optional if not found.
	 */
	Optional<Discount> getDiscount(int Discount_Id);

	void update(Discount discount, int deptId);

	List<Discount> getDiscountBytypeId(String type, int typeId);

	Optional<Discount> getDiscountByCode(String offerCode);

	List<Discount> getDiscountByType(String type);

}

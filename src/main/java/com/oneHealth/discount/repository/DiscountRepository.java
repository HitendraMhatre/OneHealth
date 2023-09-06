package com.oneHealth.discount.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.discount.entities.Discount;

/**
 * Repository interface for managing Discount.
 */

@Transactional
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

	@Modifying
    @Query("UPDATE Discount b SET b.typeId = :typeId, b.userId = :userId,"
            + " b.type = :type, b.offerName = :offerName, b.offerDescription = :offerDescription, b.offerCode"
            + " = :offerCode,"
            + " b.discountPercentage = :discountPercentage, b.startDate = :startDate, b.endDate = :endDate,"
            + " b.criteria= :criteria WHERE b.offerId = :offerId")
	void update(int typeId, int userId, String type, String offerName, String offerDescription,
			String offerCode, BigDecimal discountPercentage, Date startDate, Date endDate, String criteria,
			int offerId);
//	
//	@Modifying
//	@Query("UPDATE Discount l SET l.flag = :flag, l.function = :function" + " WHERE l.DiscountId = :DiscountId")
//	void update(int typeId, int userId, String type, String offerName, String offerDescription,
//			BigDecimal discountPercentage, Date startDate, Timestamp createdTimeStamp, Date endDate, String criteria,
//			int offerId);

	@Query(value = "SELECT * FROM discount WHERE type = :type AND type_id = :type_id", nativeQuery = true)
	List<Discount> findByTypeAndTypeId(
			@Param("type") String type,
			@Param("type_id") int type_id
			);

	Optional<Discount> findByofferCode(String offerCode);

	List<Discount> findAllBytype(String type);

}

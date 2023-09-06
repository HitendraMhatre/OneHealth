package com.oneHealth.discount.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerId_seq_gen")
    @Column(name = "offer_id")
	private int offerId;

	@Column(name="type_id")
	private int typeId;
	
	@Column(name="Created_by")
	private int userId;
	
	@Column(name="type")
	private String type;

	public Discount(int offerId, int typeId, int userId, String type, String offerCode, String offerName,
			String offerDescription, BigDecimal discountPercentage, Date startDate, Timestamp createdTimeStamp,
			Date endDate, String criteria) {
		super();
		this.offerId = offerId;
		this.typeId = typeId;
		this.userId = userId;
		this.type = type;
		this.offerCode = offerCode;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.discountPercentage = discountPercentage;
		this.startDate = startDate;
		this.createdTimeStamp = createdTimeStamp;
		this.endDate = endDate;
		this.criteria = criteria;
	}

	@Column(name="offer_code")
	private String offerCode;
	
	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	@Column(name="offer_name")
	private String offerName;

	@Column(name="offer_Description")
	private String offerDescription;

	@Column(name="discount_percentage")
	private BigDecimal discountPercentage;

	@Column(name="start_date")
	private Date startDate;
	
    @Column(name = "created_time_stamp")
    private Timestamp createdTimeStamp; 
	
	@Column(name="end_date")
	private Date endDate;
	
	public boolean isActivationStatus() {
		return activationStatus;
	}

//	public void setActivationStatus(boolean activationStatus) {
//		this.activationStatus = activationStatus;
//	}

	@Column(name="activation_status")
	private boolean activationStatus;

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	@Column(name="criteria")
	private String criteria;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}	

	public void calculateActivationStatus() {
        java.util.Date currentDate = new java.util.Date(); // Use java.util.Date for current date

        if (startDate != null && endDate != null) {
            activationStatus = !currentDate.before(startDate) && !currentDate.after(endDate);
        } else {
            activationStatus = false;
        }
    }

	public Discount() {
		super();
	}
} 
   

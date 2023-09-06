package com.oneHealth.discount.dto;

import java.sql.Time;
import java.sql.Timestamp;

public class Hospital {
	 private long hospitalId; // Unique identifier for the hospital

	 private String hospitalName; // Name of the hospital (maximum length 30 characters)

	 private String hospitalCity; // City where the hospital is located (maximum length 30 characters)
	 
	 private String hPinCode;

	 public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String gethPinCode() {
		return hPinCode;
	}

	public void sethPinCode(String hPinCode) {
		this.hPinCode = hPinCode;
	}

	public Hospital(long hospitalId, String hospitalName, String hospitalCity, String hPinCode, String hospitalAddress,
			Time hOpenTime, Time hCloseTime, String hOfficialEmail, String hOfficialContact, String hRegCertiPath,
			Timestamp hRegTimeStamp, String hGstNumber) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.hospitalCity = hospitalCity;
		this.hPinCode = hPinCode;
		this.hospitalAddress = hospitalAddress;
		this.hOpenTime = hOpenTime;
		this.hCloseTime = hCloseTime;
		this.hOfficialEmail = hOfficialEmail;
		this.hOfficialContact = hOfficialContact;
		this.hRegCertiPath = hRegCertiPath;
		this.hRegTimeStamp = hRegTimeStamp;
		this.hGstNumber = hGstNumber;
	}

	private String hospitalAddress; // Address of the hospital (maximum length 200 characters)

	 private Time hOpenTime; // Opening time of the hospital

	 private Time hCloseTime; // Closing time of the hospital

	 private String hOfficialEmail; // Official email address of the hospital (maximum length 255 characters)

	 private String hOfficialContact; // Official contact number of the hospital (maximum length 10 characters)

	 private String hRegCertiPath; // Path to the registration certificate of the hospital (maximum length 200 characters)

	 private Timestamp hRegTimeStamp; // Timestamp representing the registration time of the hospital

	 private String hGstNumber; // GST number of the hospital (maximum length 200 characters)

	 // Constructors

	

	 // Default constructor
	 public Hospital() {
		 super();
	 }

	 // Getters and setters for the above properties

	 public String getHospitalName() {
		 return hospitalName;
	 }

	 public void setHospitalName(String hospitalName) {
		 this.hospitalName = hospitalName;
	 }

	 public String getHospitalCity() {
		 return hospitalCity;
	 }

	 public void setHospitalCity(String hospitalCity) {
		 this.hospitalCity = hospitalCity;
	 }

	 public String getHospitalAddress() {
		 return hospitalAddress;
	 }

	 public void setHospitalAddress(String hospitalAddress) {
		 this.hospitalAddress = hospitalAddress;
	 }

	 public Time gethOpenTime() {
		 return hOpenTime;
	 }

	 public void sethOpenTime(Time hOpenTime) {
		 this.hOpenTime = hOpenTime;
	 }

	 public Time gethCloseTime() {
		 return hCloseTime;
	 }

	 public void sethCloseTime(Time hCloseTime) {
		 this.hCloseTime = hCloseTime;
	 }

	 public String gethOfficialEmail() {
		 return hOfficialEmail;
	 }

	 public void sethOfficialEmail(String hOfficialEmail) {
		 this.hOfficialEmail = hOfficialEmail;
	 }

	 public String gethOfficialContact() {
		 return hOfficialContact;
	 }

	 public void sethOfficialContact(String hOfficialContact) {
		 this.hOfficialContact = hOfficialContact;
	 }

	 public String gethRegCertiPath() {
		 return hRegCertiPath;
	 }

	 public void sethRegCertiPath(String hRegCertiPath) {
		 this.hRegCertiPath = hRegCertiPath;
	 }

	 public Timestamp gethRegTimeStamp() {
		 return hRegTimeStamp;
	 }

	 public void sethRegTimeStamp(Timestamp hRegTimeStamp) {
		 this.hRegTimeStamp = hRegTimeStamp;
	 }

	 public String gethGstNumber() {
		 return hGstNumber;
	 }

	 public void sethGstNumber(String hGstNumber) {
		 this.hGstNumber = hGstNumber;
	 }
}

package com.oneHealth.Hospital.repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.Hospital.entites.Hospital;

/**
 * Repository interface for managing Hospitals.
 */
@Repository
@Transactional
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    /**
     * Custom method to find Hospitals by their official email address.
     *
     * @param email_id The official email address of the Hospital to search for.
     * @return List of Hospital objects that match the provided email address.
     */
    @Query(value = "SELECT * FROM Hospital WHERE h_official_email = :email_id", nativeQuery = true)
    List<Hospital> findByEmail(@Param("email_id") String email_id);

    /**
     * Custom update method to modify Hospital details.
     *
     * @param hospitalName       The new hospital name.
     * @param hospitalCity       The new hospital city.
     * @param hospitalAddress    The new hospital address.
     * @param hOpenTime          The new hospital opening time.
     * @param hCloseTime         The new hospital closing time.
     * @param hOfficialEmail     The new hospital official email.
     * @param hOfficialContact   The new hospital official contact number.
     * @param hRegCertiPath      The new hospital registration certificate path.
     * @param hGstNumber         The new hospital GST number.
     * @param hRegTimeStamp      The new hospital registration timestamp.
     * @param hospitalId         The ID of the hospital to be updated.
     */
    @Modifying
    @Query(value = "UPDATE Hospital SET hospital_name = :hospitalName, hospital_city = :hospitalCity,"
            + " hospital_address = :hospitalAddress, h_open_time = :hOpenTime, h_close_time = :hCloseTime,"
            + " h_official_email = :hOfficialEmail, h_official_contact = :hOfficialContact,"
            + " h_reg_certi_path = :hRegCertiPath, h_gst_number = :hGstNumber, h_reg_time_stamp = :hRegTimeStamp"
            + " WHERE hospital_id = :hospitalId", nativeQuery = true)
    void update(
            @Param("hospitalName") String hospitalName,
            @Param("hospitalCity") String hospitalCity,
            @Param("hospitalAddress") String hospitalAddress,
            @Param("hOpenTime") Time hOpenTime,
            @Param("hCloseTime") Time hCloseTime,
            @Param("hOfficialEmail") String hOfficialEmail,
            @Param("hOfficialContact") String hOfficialContact,
            @Param("hRegCertiPath") String hRegCertiPath,
            @Param("hGstNumber") String hGstNumber,
            @Param("hRegTimeStamp") Timestamp hRegTimeStamp,
            @Param("hospitalId") long hospitalId
    );
}

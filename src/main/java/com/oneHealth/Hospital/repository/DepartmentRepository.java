package com.oneHealth.Hospital.repository;
import java.sql.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oneHealth.Hospital.entites.Department;

/**
 * Repository interface for managing Departments.
 */
@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    /**
     * Custom update method to modify department details.
     *
     * @param deptName     The new department name.
     * @param deptDesc     The new department description.
     * @param parentDeptId The new parent department ID.
     * @param flag         The new flag indicating if the department is active or not.
     * @param deptImgPath  The new image path for the department.
     * @param hospitalId   The new hospital ID associated with the department.
     * @param deptId       The ID of the department to be updated.
     */
    @Modifying
    @Query(value = "UPDATE Department SET dept_name = :deptName, dept_desc = :deptDesc,"
            + " parent_dept_id = :parentDeptId, flag = :flag, dept_img_path = :deptImgPath,"
            + " hospital_id = :hospitalId"
            + " WHERE dept_id = :deptId", nativeQuery = true)
    void update(
            @Param("deptName") String deptName,
            @Param("deptDesc") String deptDesc,
            @Param("parentDeptId") Integer parentDeptId,
            @Param("flag") Boolean flag,
            @Param("deptImgPath") String deptImgPath,
            @Param("hospitalId") Integer hospitalId,
            @Param("deptId") int deptId
    );
}


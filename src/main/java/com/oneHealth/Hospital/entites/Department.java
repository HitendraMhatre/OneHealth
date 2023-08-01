package com.oneHealth.Hospital.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId; // Unique identifier for the department

    @Column(length = 50)
    private String deptName; // Name of the department (maximum length 50 characters)

    @Column(length = 1000)
    private String deptDesc; // Description of the department (maximum length 1000 characters)

    private Integer parentDeptId; // ID of the parent department, if applicable

    private Boolean flag; // A flag to indicate the status of the department

    @Column(length = 255)
    private String deptImgPath; // Path to the image associated with the department (maximum length 255 characters)

    private Integer hospitalId; // ID of the hospital to which the department belongs

    // Getters and setters for the above properties

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public Integer getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(Integer parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getDeptImgPath() {
        return deptImgPath;
    }

    public void setDeptImgPath(String deptImgPath) {
        this.deptImgPath = deptImgPath;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    // Constructors

    // Default constructor
    public Department() {
        super();
    }

    // Parameterized constructor to initialize all properties
    public Department(String deptName, String deptDesc, Integer parentDeptId, Boolean flag, String deptImgPath,
            Integer hospitalId) {
        super();
        this.deptName = deptName;
        this.deptDesc = deptDesc;
        this.parentDeptId = parentDeptId;
        this.flag = flag;
        this.deptImgPath = deptImgPath;
        this.hospitalId = hospitalId;
    }
}

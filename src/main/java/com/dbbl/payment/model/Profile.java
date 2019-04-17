package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/*
CREATE TABLE profile (
                id NUMBER NOT NULL,
                first_name VARCHAR2(50) NOT NULL,
                last_name VARCHAR2(50) NOT NULL,
                date_of_birth DATE NOT NULL,
                email VARCHAR2(100) NOT NULL,
                nationality VARCHAR2(50) NOT NULL,
                sex VARCHAR2(10) NOT NULL,
                mobile_number VARCHAR2(30) NOT NULL,
                occupation VARCHAR2(50) NOT NULL,
                designation VARCHAR2(50) NOT NULL,
                national_id VARCHAR2(30) NOT NULL,
                passport_id VARCHAR2(30) NOT NULL,
                tin_number VARCHAR2(30) NOT NULL,
                updated_date TIMESTAMP NOT NULL,
                created_date TIMESTAMP NOT NULL,
                created_by NUMBER NOT NULL,
                updated_by NUMBER NOT NULL,
                CONSTRAINT PROFILE_PK PRIMARY KEY (id)
);
 */

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 4, max = 30, message = "First name length will be in between 4 to 30 characters")
    private String firstName;
    @Size(min = 4, max = 30, message = "Last name length will be in between 4 to 30 characters")
    private String lastName;
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    private String email;
    @Size(min = 4, max = 30, message = "Country is required")
    private String nationality;
    @Size(min = 4, max = 30, message = "Gender is required")
    private String sex;
    @NotBlank(message = "Contact is required")
    private String mobileNumber;
    @Size(min = 4, max = 40, message = "Occupation is required")
    private String occupation;
    @Size(min = 4, max = 40, message = "Designation is required")
    private String designation;
    @Size(min = 8, max =30, message = "NationalID card length will be in between 10 to 30 characters")
    private String nationalId;
    private String passportId;
    private String tinNumber;
    private Long createdBy;
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;
    private Long updatedBy;
    @Temporal(value = TemporalType.DATE)
    private Date updatedDate;
    @JsonIgnore
    @OneToMany(mappedBy = "profileId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Transient
    private Set<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                ", sex='" + sex + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", occupation='" + occupation + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", passportId='" + passportId + '\'' +
                ", tinNumber='" + tinNumber + '\'' +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                '}';
    }
}

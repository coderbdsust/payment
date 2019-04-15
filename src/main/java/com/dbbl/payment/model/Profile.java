package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
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
    private String firstName;
    private String lastName;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;
    private String email;
    private String nationality;
    private String sex;
    private String mobileNumber;
    private String occupation;
    private String nationalId;
    private String passportId;
    private String tinNumber;
    private Long createdBy;
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;
    private Long updatedBy;
    @Temporal(value = TemporalType.DATE)
    private Date updatedDate;
    @OneToMany(mappedBy = "profileId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Address> addresses;
}

package com.dbbl.payment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
/*
CREATE TABLE user_role (
                id NUMBER NOT NULL,
                role_name VARCHAR2(30) NOT NULL,
                user_id NUMBER NOT NULL,
                CONSTRAINT USER_ROLE_PK PRIMARY KEY (id)
);
 */

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private UserAccount userId;
}

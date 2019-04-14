package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/*
CREATE TABLE user_account (
                id NUMBER NOT NULL,
                email VARCHAR2(100) NOT NULL,
                password VARCHAR2(128) NOT NULL,
                enabled NUMBER NOT NULL,
                deleted NUMBER NOT NULL,
                profile_id NUMBER NOT NULL,
                locked NUMBER NOT NULL,
                CONSTRAINT USER_ACCOUNT_PK PRIMARY KEY (id)
);
*/

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private boolean enabled;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    @JsonIgnore
    private Profile profileId;
    private boolean locked;
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UserRole> userRoles;
}

package com.dbbl.payment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    public UserRole(){
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public UserRole(String roleName, UserAccount userAccount) {
        this.roleName = roleName;
        this.userAccount = userAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id.equals(userRole.id) &&
                roleName.equals(userRole.roleName) &&
                userAccount.equals(userRole.userAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, userAccount);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

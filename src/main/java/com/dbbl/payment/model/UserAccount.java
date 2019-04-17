package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
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
    private boolean enabled=true;
    private boolean deleted=false;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    private Profile profileId;
    private boolean locked=false;

    @JsonIgnore
    @OneToMany(
            mappedBy = "userAccount",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<UserRole> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return enabled == that.enabled &&
                deleted == that.deleted &&
                locked == that.locked &&
                Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(profileId, that.profileId) &&
                Objects.equals(userRoles, that.userRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, enabled, deleted, profileId, locked);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", deleted=" + deleted +
                ", profileId=" + profileId +
                ", locked=" + locked +
                ", userRoles=" + userRoles +
                '}';
    }
}

package com.dbbl.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_generator_seq", initialValue = 1000001)
    private Long id;
    private Boolean active;
    private Long branchId;
    private Boolean locked;
    private Long createdBy;
    @Temporal(value = TemporalType.DATE)
    private Date createdDate;
    private Long updatedBy;
    @Temporal(value = TemporalType.DATE)
    private Date updatedDate;
    @ManyToOne
    @JoinColumn(name = "profile_id",referencedColumnName = "id")
    @JsonIgnore
    private Profile profileId;
    @OneToMany(mappedBy = "customerId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<BeneficiarAccount> beneficiarAccounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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

    public Profile getProfileId() {
        return profileId;
    }

    public void setProfileId(Profile profileId) {
        this.profileId = profileId;
    }

    public Set<BeneficiarAccount> getBeneficiarAccounts() {
        return beneficiarAccounts;
    }

    public void setBeneficiarAccounts(Set<BeneficiarAccount> beneficiarAccounts) {
        this.beneficiarAccounts = beneficiarAccounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", active=" + active +
                ", branchId=" + branchId +
                ", locked=" + locked +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
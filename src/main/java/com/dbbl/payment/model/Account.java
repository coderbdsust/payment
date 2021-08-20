package com.dbbl.payment.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_number_generator")
	@SequenceGenerator(name = "account_number_generator", sequenceName = "account_number_generator_seq", initialValue = 1000001)
	private Long id;
	private Float balance = 0.0f;
	private Long branchId;
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customerId;
	private boolean enabled;
	private Long accountType;
	private Long createdBy;
	@Temporal(value = TemporalType.DATE)
	private Date createdDate;
	private Long updatedBy;
	@Temporal(value = TemporalType.DATE)
	private Date updatedDate;
	private Long bankProductId;
	@OneToMany(mappedBy = "accountId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AccountTransanctionHistory> accountTransanctionHistories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getAccountType() {
		return accountType;
	}

	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}

	public Long getBankProductId() {
		return bankProductId;
	}

	public void setBankProductId(Long bankProductId) {
		this.bankProductId = bankProductId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

//    public String getAccountNo() {
//		return accountNo;
//	}
//
//	public void setAccountNo(String accountNo) {
//		this.accountNo = accountNo;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Set<AccountTransanctionHistory> getAccountTransanctionHistories() {
		return accountTransanctionHistories;
	}

	public void setAccountTransanctionHistories(Set<AccountTransanctionHistory> accountTransanctionHistories) {
		this.accountTransanctionHistories = accountTransanctionHistories;
	}
}

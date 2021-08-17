package com.dbbl.payment.dto;

import com.dbbl.payment.model.Account;
import com.dbbl.payment.model.Address;
import com.dbbl.payment.model.Customer;
import com.dbbl.payment.model.Profile;

import javax.validation.Valid;

public class CustomerDto {
	@Valid
	private Customer customer;
	@Valid
	private Account account;
	@Valid
	private Profile profile;
	@Valid
	private Address presentAddress;
	@Valid
	private Address parmanentAddress;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(Address presentAddress) {
		this.presentAddress = presentAddress;
	}

	public Address getParmanentAddress() {
		return parmanentAddress;
	}

	public void setParmanentAddress(Address parmanentAddress) {
		this.parmanentAddress = parmanentAddress;
	}

	@Override
	public String toString() {
		return "CustomerDto{" + "customer=" + customer + ", profile=" + profile + ", presentAddress=" + presentAddress
				+ ", parmanentAddress=" + parmanentAddress + '}';
	}
}

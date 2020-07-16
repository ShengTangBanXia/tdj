package com.tdj.logistics_system.models.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 员工类
 * 
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;

    private String customerSex;

    private String customerPosition;

    private String customerTelephone;

    private String customerEmail;

    private String customerAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date customerEntrytime;

    private String customerDiploma;

    private String customerDepartement;

    private String accountNumber;

    private String password;

    private Date customerBirthday;
    @Transient
    private List<Role> roles;
    
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getCustomerPosition() {
		return customerPosition;
	}

	public void setCustomerPosition(String customerPosition) {
		this.customerPosition = customerPosition;
	}

	public String getCustomerTelephone() {
		return customerTelephone;
	}

	public void setCustomerTelephone(String customerTelephone) {
		this.customerTelephone = customerTelephone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getCustomerEntrytime() {
		return customerEntrytime;
	}

	public void setCustomerEntrytime(Date customerEntrytime) {
		this.customerEntrytime = customerEntrytime;
	}

	public String getCustomerDiploma() {
		return customerDiploma;
	}

	public void setCustomerDiploma(String customerDiploma) {
		this.customerDiploma = customerDiploma;
	}

	public String getCustomerDepartement() {
		return customerDepartement;
	}

	public void setCustomerDepartement(String customerDepartement) {
		this.customerDepartement = customerDepartement;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCustomerBirthday() {
		return customerBirthday;
	}

	public void setCustomerBirthday(Date customerBirthday) {
		this.customerBirthday = customerBirthday;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerSex=" + customerSex
				+ ", customerPosition=" + customerPosition + ", customerTelephone=" + customerTelephone
				+ ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + ", customerEntrytime="
				+ customerEntrytime + ", customerDiploma=" + customerDiploma + ", customerDepartement="
				+ customerDepartement + ", accountNumber=" + accountNumber + ", password=" + password
				+ ", customerBirthday=" + customerBirthday + "]";
	}
	
}
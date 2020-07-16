package com.tdj.logistics_system.models.account.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="courier")
public class Courier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courierId;
	private String courierName;
	private String courierSex;
	private String courierTelephone;
	  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date courierEntrytime;
	private int status;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCourierId() {
		return courierId;
	}
	public void setCourierId(int courierId) {
		this.courierId = courierId;
	}
	public String getCourierName() {
		return courierName;
	}
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	public String getCourierSex() {
		return courierSex;
	}
	public void setCourierSex(String courierSex) {
		this.courierSex = courierSex;
	}
	public String getCourierTelephone() {
		return courierTelephone;
	}
	public void setCourierTelephone(String courierTelephone) {
		this.courierTelephone = courierTelephone;
	}
	public Date getCourierEntrytime() {
		return courierEntrytime;
	}
	public void setCourierEntrytime(Date courierEntrytime) {
		this.courierEntrytime = courierEntrytime;
	}	
}

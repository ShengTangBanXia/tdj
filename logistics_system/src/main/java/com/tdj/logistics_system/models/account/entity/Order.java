package com.tdj.logistics_system.models.account.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 订单类
 *
 */
@Entity
@Table(name = "logistics_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private String orderNum;
	
	private String orderSender;
	private String senderAddress;
	private String senderTel;
	
	private String orderReceiver;
	private String receiverAddress;
	private String receiverTel;
	
	private String packageType;
	private int volume;
	private int weight;
	
	private String carryMethod;
	private String payMethod;
	
	private String orderPrincipal;
	private String orderStatus;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date inputTime;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date finishTime;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getOrderSender() {
		return orderSender;
	}

	public void setOrderSender(String orderSender) {
		this.orderSender = orderSender;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getOrderReceiver() {
		return orderReceiver;
	}

	public void setOrderReceiver(String orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCarryMethod() {
		return carryMethod;
	}

	public void setCarryMethod(String carryMethod) {
		this.carryMethod = carryMethod;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getOrderPrincipal() {
		return orderPrincipal;
	}

	public void setOrderPrincipal(String orderPrincipal) {
		this.orderPrincipal = orderPrincipal;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}


	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNum=" + orderNum + ", orderSender=" + orderSender
				+ ", senderAddress=" + senderAddress + ", senderTel=" + senderTel + ", orderReceiver=" + orderReceiver
				+ ", receiverAddress=" + receiverAddress + ", receiverTel=" + receiverTel + ", packageType="
				+ packageType + ", volume=" + volume + ", weight=" + weight + ", carryMethod=" + carryMethod
				+ ", payMethod=" + payMethod + ", orderPrincipal=" + orderPrincipal + ", orderStatus=" + orderStatus
				+ ", inputTime=" + inputTime + ", finishTime=" + finishTime + "]";
	}


	

	
	
	
	
}

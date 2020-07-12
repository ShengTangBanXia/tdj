package com.tdj.logistics_system.models.account.entity;

public class Address {

	private int id;
	private int pid;
	private String name;	
	
	public Address() {
		super();
	}
	
	public Address(int id, int pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}
	
}

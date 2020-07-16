package com.tdj.logistics_system.models.account.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    private String roleDescription;

    private String roleDepart;

  
    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getroleName() {
        return roleName;
    }

    public void setroleName(String roleName) {
        this.roleName = roleName;
    }

    public String getroleDescription() {
        return roleDescription;
    }

    public void setroleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getroleDepart() {
        return roleDepart;
    }

    public void setroleDepart(String roleDepart) {
        this.roleDepart = roleDepart;
    }
}
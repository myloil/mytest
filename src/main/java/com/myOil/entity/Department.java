/**
 * 
 */
package com.myOil.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Shuai.yang
 *
 */
@Entity
@Table(name = "department")
public class Department{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "departmentId")
	private int departmentId;

	@Column(name = "departmentName")
	private String departmentName;
	
	@ManyToMany(fetch= FetchType.EAGER)
	@JoinTable(name = "role", joinColumns = { @JoinColumn(name = "roleId", referencedColumnName = "departmentId") }, inverseJoinColumns = { @JoinColumn(name = "authorityId", referencedColumnName = "authorityId") })
	private Set<Authority> authorityList = new HashSet<Authority>();

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(Set<Authority> authorityList) {
		this.authorityList = authorityList;
	}
}

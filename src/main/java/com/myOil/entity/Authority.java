/**
 * 
 */
package com.myOil.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author YangShuai
 *
 */
@Entity
@Table(name = "authority")
public class Authority{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authorityId")
	private int authorityId;

	@Column(name = "authorityUrl")
	private String authorityUrl;

	@Column(name = "authorityDesc")
	private String authorityDesc;

	@Column(name = "authorityCataId")
	private int authorityCataId;

	@Column(name = "createTimestamp")
	private Timestamp createTimestamp;

	@JsonIgnore
	@ManyToMany(mappedBy = "authorityList",fetch= FetchType.EAGER)
	private Set<Department> departmentList = new HashSet<Department>();

	public Set<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(Set<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityUrl() {
		return authorityUrl;
	}

	public void setAuthorityUrl(String authorityUrl) {
		this.authorityUrl = authorityUrl;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public int getAuthorityCataId() {
		return authorityCataId;
	}

	public void setAuthorityCataId(int authorityCataId) {
		this.authorityCataId = authorityCataId;
	}

}

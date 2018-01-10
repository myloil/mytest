/**
 * 
 */
package com.myOil.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Shuai.yang
 *
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
	private int userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "phone")
	private String phone;

	@Column(name = "idCard")
	private String idCard;

	@Column(name = "departmentId")
	private int departmentId;

	@Column(name = "gasStationId")
	private int gasStationId;

	@Column(name = "createTime")
	private Timestamp createTimestamp;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public int getGasStationId() {
		return gasStationId;
	}

	public void setGasStationId(int gasStationId) {
		this.gasStationId = gasStationId;
	}



	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
}

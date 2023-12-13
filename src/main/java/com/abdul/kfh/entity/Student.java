package com.abdul.kfh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "st_id")
	public long stId;
    
	
	@NotBlank
	@Column(name = "stEg_name", length = 90)
	public String stEgName;
	
	@NotBlank
	@Column(name = "stAr_name", length = 90)
	public String stArName;
	
	
	@Column(name = "st_email", length = 90)
	@Email
	@NotBlank
	public String stEmail;
	
	@NotBlank
	@Column(name = "st_address", length = 512)
	public String st_address;

	@Column(name = "st_status")
	public int stStatus;

	public long getStId() {
		return stId;
	}

	public void setStId(long stId) {
		this.stId = stId;
	}

	public String getStEgName() {
		return stEgName;
	}

	public void setStEgName(String stEgName) {
		this.stEgName = stEgName;
	}

	public String getStArName() {
		return stArName;
	}

	public void setStArName(String stArName) {
		this.stArName = stArName;
	}

	public String getStEmail() {
		return stEmail;
	}

	public void setStEmail(String stEmail) {
		this.stEmail = stEmail;
	}

	public String getSt_address() {
		return st_address;
	}

	public void setSt_address(String st_address) {
		this.st_address = st_address;
	}

	public int getStStatus() {
		return stStatus;
	}

	public void setStStatus(int stStatus) {
		this.stStatus = stStatus;
	}
	
	
	
	
	
    
	
}


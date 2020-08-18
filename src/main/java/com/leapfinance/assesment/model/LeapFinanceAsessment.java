package com.leapfinance.assesment.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="leapfinanceassesment")
public class LeapFinanceAsessment {

	@Id
	@Column(name="userid")
	private String userId;
	
	@Column(name ="password")
	private String password;
	
	@Column(name="loggedintime")
	private LocalDateTime loggedInTime;
	
	@Column(name="loggedinmessage")
	private String loggedInMessage;
	
	@OneToMany(mappedBy = "leapFinanceAssesment")
	private List<SaveUrlContent> urlContent;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(LocalDateTime loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	public String getLoggedInMessage() {
		return loggedInMessage;
	}

	public void setLoggedInMessage(String loggedInMessage) {
		this.loggedInMessage = loggedInMessage;
	}

	public List<SaveUrlContent> getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(List<SaveUrlContent> urlContent) {
		this.urlContent = urlContent;
	}
	
	
	
	
	
	
}

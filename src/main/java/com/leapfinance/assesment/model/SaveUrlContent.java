package com.leapfinance.assesment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="saveurlcontent")
public class SaveUrlContent {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="url")
	private String url;
	
	@Column(name="urlcontent")
	private String urlContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private LeapFinanceAsessment leapFinanceAssesment;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(String urlContent) {
		this.urlContent = urlContent;
	}

	public LeapFinanceAsessment getLeapFinanceAssesment() {
		return leapFinanceAssesment;
	}

	public void setLeapFinanceAssesment(LeapFinanceAsessment leapFinanceAssesment) {
		this.leapFinanceAssesment = leapFinanceAssesment;
	}
	
	
	
	
}

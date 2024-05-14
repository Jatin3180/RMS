package com.management.rms.model;

public class JobVO {

	private String Title;
	
	private String Description;
	
	private String PostedOn;
	
	private int TotalApplications;
	
	private String CompanyName;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPostedOn() {
		return PostedOn;
	}

	public void setPostedOn(String postedOn) {
		PostedOn = postedOn;
	}

	public int getTotalApplications() {
		return TotalApplications;
	}

	public void setTotalApplications(int totalApplications) {
		TotalApplications = totalApplications;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	
	
	
	
	
}

package com.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobDTO extends AuditableDTO {

	private String jobId;
	
	private String title;
	private String description;
	private String startDate;
	private String companyName;
	private String additionalInfo;
	
	private String categoryId;
	
	private String city;
	private String state;
	private Integer zipCode;
	private String country;
	private double lat;
	private double lng;
	
	private String skills;
	private String yearsOfExpNeeded;
	private String salaryType;
	private String salary;
	private String salaryCurrency;
	
	private String contactEmail;
	private String contactPhone;
	
	public String getCompleteAddress()
	{
		return city + "," + state;
	}
	
}

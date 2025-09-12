package com.jobportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter @Setter @NoArgsConstructor
public class Job extends Auditable {
	
	@Id
	private String jobId;
	
	@TextIndexed private String title;
	@TextIndexed private String description;
	
	private String startDate;
	private String companyName;
	private String additionalInfo;
	
	private String categoryId;
	
	@TextIndexed private String city;
	private String state;
	private Integer zipCode;
	private String country;
	private Point point;
	
	private String skills;
	private String yearsOfExpNeeded;
	private String salaryType;
	private String salary;
	private String salaryCurrency;
	
	private String contactEmail;
	private String contactPhone;

}

package com.jobportal.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class EmployerDTO extends AuditableDTO {

	private String employerId;
	
	private String name;
	private String email;
	private String companyName;
	
	private String personalIntro; // link to s3 location of audio/ video
	private String additionalInfo;

	private double lat;
	private double lng;
	
	private List<ReviewDTO> reviews;
	private List<ReferenceDTO> references;
	private List<MessageDTO> messages;
}

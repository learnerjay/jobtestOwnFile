package com.jobportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReferenceDTO extends AuditableDTO {

	private String referenceId;
	
	private String name;
	private String company;
	private String email;
	private String phoneNumber;
	private String relation;
	
}


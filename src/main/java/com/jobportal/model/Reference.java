package com.jobportal.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Reference extends Auditable {

	@Id
	private String referenceId;
	
	private String name;
	private String company;
	private String email;
	private String phoneNumber;
	private String relation;
	
}

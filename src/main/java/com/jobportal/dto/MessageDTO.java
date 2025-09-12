package com.jobportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MessageDTO extends AuditableDTO {
	
	private String message;
	private String postedBy;

}


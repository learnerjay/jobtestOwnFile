package com.jobportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuditableDTO {

	private String createdOn;
	private String updatedOn;

}

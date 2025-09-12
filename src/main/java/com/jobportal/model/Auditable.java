package com.jobportal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Auditable {
	
	private String createdOn;
	private String updatedOn;

}

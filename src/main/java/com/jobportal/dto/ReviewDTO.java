package com.jobportal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReviewDTO extends AuditableDTO {

	private String reviewId;
	
	private Integer stars;
	private String comments;
	private String reviewFrom;
	
}

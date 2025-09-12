package com.jobportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Review extends Auditable {

	@Id
	private String reviewId;
	
	private Integer stars;
	@TextIndexed private String comments;
	private String reviewFrom;
	
}
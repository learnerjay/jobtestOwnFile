package com.jobportal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Message extends Auditable {
	
	private String message;
	private String postedBy;

}

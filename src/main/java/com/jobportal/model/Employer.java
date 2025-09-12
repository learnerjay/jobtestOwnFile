package com.jobportal.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Employer extends Auditable {

	@Id
	private String employerId;
	
	private String name;
	private String email;
	private String companyName;
	private Point point; // location
	
	private String personalIntro; // link to s3 location of audio/ video
	private String additionalInfo;
	
	private List<Review> reviews;
	private List<Reference> references;
	private List<Message> messages;
}


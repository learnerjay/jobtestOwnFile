package com.jobportal.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.TextIndexed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Candidate extends Auditable {

	@Id
	private String candidateId;
	
	private String name;
	private String email;
	@TextIndexed private String skills;
	private String desiredJobType; // FT/PT
	private Point point; // location
	
	private String resume; // link to s3 location
	private String coverLetter;
	private String linkedInProfile;
	private String personalIntro; // link to s3 location of audio/ video
	private String additionalInfo;
	
	private List<Review> reviews;
	private List<Reference> references;
	private List<Message> messages;
	
}

package com.jobportal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateDTO extends AuditableDTO {
	
	private String candidateId;
	
	private String name;
	private String email;
	private String skills;
	private String desiredJobType;

	private String resume;
	private String coverLetter;
	private String linkedInProfile;
	private String personalIntro; // link to s3 location of audio/ video
	private String additionalInfo;
	
	private double lat;
	private double lng;
	
	private List<ReviewDTO> reviews;
	private List<ReferenceDTO> references;
	private List<MessageDTO> messages;
	
	public Integer getReviewCount()
	{
		return (reviews == null ? 0 : reviews.size());
	}

	public Integer getMessageCount()
	{
		return (messages == null ? 0 : messages.size());
	}
}

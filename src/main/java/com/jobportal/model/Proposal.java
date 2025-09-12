package com.jobportal.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Proposal extends Auditable {

	@Id
	private String proposalId;
	private String jobId;
	
	private String coverLetter;
	
	private String bidPrice;
	private String estimatedTime;
	
	private String contactEmail;
	private String contactPhone;
	private Point point;
	
	private ProposalStatus status;
	private List<Message> messages;
}

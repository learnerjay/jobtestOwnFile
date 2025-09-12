package com.jobportal.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JobContactViewDTO extends AuditableDTO {
	
	private String jobId;	
	private String title;
	private String description;
	
	private List<ProposalDTO> proposals;
	
	public Integer getProposalCount()
	{
		return (proposals == null ? 0 : proposals.size());
	}
}

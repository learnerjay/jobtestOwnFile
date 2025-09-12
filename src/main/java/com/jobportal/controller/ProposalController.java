package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ProposalDTO;
import com.jobportal.service.ProposalService;
import com.jobportal.service.SearchService;

@RestController
@RequestMapping("/proposals")
@CrossOrigin("*")
public class ProposalController {
	
	@Autowired
	ProposalService proposalService;
	
	@Autowired
	SearchService searchService;
	
	
	@GetMapping
	public List<ProposalDTO> findAll()
	{
		return proposalService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ProposalDTO findById(@PathVariable( "id" ) String id)
	{
		return proposalService.findById(id);
	}
	
	@GetMapping(value = "/jobs/{jobId}")
	public List<ProposalDTO> findByJobId(@PathVariable( "jobId" ) String jobId)
	{
		return proposalService.findByJobId(jobId);
	}
	
	@PostMapping
	public String createProposal(@RequestBody ProposalDTO proposalDTO)
	{
		return proposalService.createProposal(proposalDTO);
	}
	
	@PutMapping
	public void updateProposal(@RequestBody ProposalDTO proposalDTO)
	{
		proposalService.updateProposal(proposalDTO);
	}
 
}

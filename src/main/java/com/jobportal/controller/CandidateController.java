package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.CandidateDTO;
import com.jobportal.service.CandidateService;
import com.jobportal.service.SearchService;

@RestController
@RequestMapping("/candidates")
@CrossOrigin("*")
public class CandidateController {
	
	@Autowired
	CandidateService candidateService;
	
	@Autowired
	SearchService searchService;
	
	
	@GetMapping
	public List<CandidateDTO> findAll()
	{
		return candidateService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public CandidateDTO findById(@PathVariable( "id" ) String id)
	{
		return candidateService.findById(id);
	}
	
	@PostMapping
	public String createCandidate(@RequestBody CandidateDTO cndtDTO)
	{
		return candidateService.createCandidate(cndtDTO);
	}
	
	@PutMapping
	public void updateCandidate(@RequestBody CandidateDTO cndtDTO)
	{
		candidateService.updateCandidate(cndtDTO);
	}
	
	@GetMapping(value = "/search")
	public List<CandidateDTO> search(@RequestParam String term, Pageable p)
	{
		return searchService.searchCandidatesByTerm(term, p);
	}
 
}

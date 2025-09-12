package com.jobportal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dao.SearchDAO;
import com.jobportal.dto.CandidateDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.model.Candidate;
import com.jobportal.model.Job;

@Service
public class SearchService {
	
	@Autowired
	SearchDAO dao;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<JobDTO> searchJobsByTerm(String term, Pageable p)
	{
		return dao.searchByTerm(Job.class, term, p).stream().map(job -> 
			modelMapper.map(job, JobDTO.class)).collect(Collectors.toList());
	}
	
	public List<CandidateDTO> searchCandidatesByTerm(String term, Pageable p)
	{
		return dao.searchByTerm(Candidate.class, term, p).stream().map(cndt -> 
			modelMapper.map(cndt, CandidateDTO.class)).collect(Collectors.toList());
	}

}

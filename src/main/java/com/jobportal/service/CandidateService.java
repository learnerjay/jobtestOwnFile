package com.jobportal.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dao.CandidateDAO;
import com.jobportal.dto.CandidateDTO;
import com.jobportal.model.Candidate;

@Service
public class CandidateService {

	@Autowired
	CandidateDAO dao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	
	public List<CandidateDTO> findAll()
	{
		return dao.findAll().stream().map(cndt -> 
			modelMapper.map(cndt, CandidateDTO.class)).collect(Collectors.toList());
	}
	
	public CandidateDTO findById(String id)
	{
		Optional<Candidate> optCndt = dao.findById(id);
		
		if (!optCndt.isPresent())
			return null;
		
		return modelMapper.map(optCndt.get(), CandidateDTO.class);
	}
	
	@Transactional
	public String createCandidate(CandidateDTO cndtDTO)
	{
		Candidate cndt = modelMapper.map(cndtDTO, Candidate.class);
		cndt.setCreatedOn((new Date()).toString());
		cndt.setUpdatedOn(cndt.getCreatedOn());
		
		Point point = new Point(cndtDTO.getLng(), cndtDTO.getLat());
		cndt.setPoint(point);
		
		dao.save(cndt);
		
		return cndt.getCandidateId();
	}
	
	
	@Transactional
	public void updateCandidate(CandidateDTO cndtDTO)
	{
		Optional<Candidate> optCndt = dao.findById(cndtDTO.getCandidateId());
		
		if (!optCndt.isPresent())
			return;
		
		Candidate cndt = optCndt.get();
		cndt.setUpdatedOn((new Date()).toString());
		
		Point point = new Point(cndtDTO.getLng(), cndtDTO.getLat());
		cndt.setPoint(point);
		
		modelMapperService.getNonNullModelMapper().map(cndtDTO, cndt);
		
		dao.save(cndt);
	}

}

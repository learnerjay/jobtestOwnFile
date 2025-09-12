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

import com.jobportal.dao.ProposalDAO;
import com.jobportal.dto.ProposalDTO;
import com.jobportal.model.Proposal;

@Service
public class ProposalService {
	
	@Autowired
	ProposalDAO dao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	@Autowired
	LocationService locationService;
	
	
	public List<ProposalDTO> findAll()
	{
		return dao.findAll().stream().map(proposal -> 
			modelMapper.map(proposal, ProposalDTO.class)).collect(Collectors.toList());
	}
	
	public ProposalDTO findById(String id)
	{
		Optional<Proposal> optProposal = dao.findById(id);
		
		if (!optProposal.isPresent())
			return null;
		
		return modelMapper.map(optProposal.get(), ProposalDTO.class);
	}
	
	public List<ProposalDTO> findByJobId(String jobId)
	{
		return dao.findByJobId(jobId).stream().map(proposal -> 
			modelMapper.map(proposal, ProposalDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public String createProposal(ProposalDTO proposalDTO)
	{
		Proposal proposal = modelMapper.map(proposalDTO, Proposal.class);
		proposal.setCreatedOn((new Date()).toString());
		proposal.setUpdatedOn(proposal.getCreatedOn());
		
		Point point = new Point(proposalDTO.getLng(), proposalDTO.getLat());
		proposal.setPoint(point);
		
		dao.save(proposal);
		
		//locationService.findByAddress(job.getProposalId(), jobDTO.getCompleteAddress());
		
		return proposal.getProposalId();
	}
	
	
	@Transactional
	public void updateProposal(ProposalDTO proposalDTO)
	{
		Optional<Proposal> optProposal = dao.findById(proposalDTO.getProposalId());
		
		if (!optProposal.isPresent())
			return;
		
		Proposal proposal = optProposal.get();
		proposal.setUpdatedOn((new Date()).toString());
		
		Point point = new Point(proposalDTO.getLng(), proposalDTO.getLat());
		proposal.setPoint(point);
		
		modelMapperService.getNonNullModelMapper().map(proposalDTO, proposal);
		
		dao.save(proposal);
	}
}

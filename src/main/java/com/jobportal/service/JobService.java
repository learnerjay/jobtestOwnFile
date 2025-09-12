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

import com.jobportal.dao.JobDAO;
import com.jobportal.dto.JobContactViewDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.dto.LocationDTO;
import com.jobportal.model.Job;

@Service
public class JobService {
	
	@Autowired
	JobDAO dao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	ProposalService proposalService;
	
	
	public List<JobDTO> findAll()
	{
		return dao.findAll().stream().map(job -> 
			modelMapper.map(job, JobDTO.class)).collect(Collectors.toList());
	}
	
	public JobDTO findById(String id)
	{
		Optional<Job> optJob = dao.findById(id);
		
		if (!optJob.isPresent())
			return null;
		
		return modelMapper.map(optJob.get(), JobDTO.class);
	}
	
	public List<JobContactViewDTO> findByContactEmail(String contactEmail)
	{
		List jobs1 = dao.findByContactEmail(contactEmail);
		
		List<JobContactViewDTO> jobs = dao.findByContactEmail(contactEmail).stream().map(job -> 
			modelMapper.map(job, JobContactViewDTO.class)).collect(Collectors.toList());
		
		// fill in the proposal details
		jobs.forEach(job -> {
			job.setProposals(proposalService.findByJobId(job.getJobId()));
		});
		
		return jobs;
	}
	
	@Transactional
	public String createJob(JobDTO jobDTO)
	{
		Job job = modelMapper.map(jobDTO, Job.class);
		job.setCreatedOn((new Date()).toString());
		job.setUpdatedOn(job.getCreatedOn());
		
		Point point = new Point(jobDTO.getLng(), jobDTO.getLat());
		job.setPoint(point);
		
		dao.save(job);
		
		//locationService.findByAddress(job.getJobId(), jobDTO.getCompleteAddress());
		
		return job.getJobId();
	}
	
	
	@Transactional
	public void updateJob(JobDTO jobDTO)
	{
		Optional<Job> optJob = dao.findById(jobDTO.getJobId());
		
		if (!optJob.isPresent())
			return;
		
		Job job = optJob.get();
		job.setUpdatedOn((new Date()).toString());
		
		Point point = new Point(jobDTO.getLng(), jobDTO.getLat());
		job.setPoint(point);
		
		modelMapperService.getNonNullModelMapper().map(jobDTO, job);
		
		dao.save(job);
	}
	
	@Transactional
	public void updateLocation(String jobId, LocationDTO location)
	{
		Optional<Job> optJob = dao.findById(jobId);
		
		if (!optJob.isPresent())
			return;
		
		Job job = optJob.get();
		Point point = new Point(location.getLongitude(), location.getLatitude());
		
		job.setPoint(point);
		
		dao.save(job);
	}
}

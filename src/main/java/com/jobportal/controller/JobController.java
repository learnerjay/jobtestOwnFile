package com.jobportal.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
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

import com.jobportal.dto.JobContactViewDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.service.JobService;
import com.jobportal.service.SearchService;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
@Slf4j
public class JobController {

	@Autowired
	JobService jobService;

	@Autowired
	SearchService searchService;

	private String classLevel = "Set at class level";


	@GetMapping
	public List<JobDTO> findAll()
	{
		log.debug("Entering method findAll");
		log.info("Database operation: FINDALL - findAll");
		log.info("EXITING: findAll() method - Successfully completed");
		return jobService.findAll();
	}

	@GetMapping(value = "/{id}")
	public JobDTO findById(@PathVariable( "id" ) String id)
	{
		classLevel = "changing";
		return jobService.findById(id);
	}

	@GetMapping(value = "/contacts/{contactEmail}")
	public List<JobContactViewDTO> findByContactEmail(@PathVariable( "contactEmail" ) String contactEmail)
	{
		return jobService.findByContactEmail(contactEmail);
	}

	@PostMapping
	public String createJob(@RequestBody JobDTO jobDTO)
	{
		log.debug("ENTERING: createJob() method");
		String result = "";
		try {
			result = jobService.createJob(jobDTO);
		} catch (Exception e) {
			e.printStackTrace();;
		log.error("EXCEPTION in createJob() method for no parameters: {}", e.getMessage(), e);
		}
		log.info("EXITING: createJob() method - Successfully completed");
		return result;
	}

	@PutMapping
	public void updateJob(@RequestBody JobDTO jobDTO)
	{
		log.debug("ENTERING: updateJob() method");
		String testValue = "Testing my local changes";
		testValue = "value changed, needs logging";

		jobService.updateJob(jobDTO);
	log.info("EXITING: updateJob() method - Successfully completed");
	}

	@GetMapping(value = "/search")
	public List<JobDTO> search(@RequestParam String term, Pageable p)
	{
		log.debug("ENTERING: search() method");
		log.info("EXITING: search() method - Successfully completed");
		return searchService.searchJobsByTerm(term, p);
	}

}

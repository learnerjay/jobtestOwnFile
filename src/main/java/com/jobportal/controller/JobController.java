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
		log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "JobController", "findAll", durationMs, operationsPerformed);
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
		log.info("API_ENTRY: {} {} - {}.{}() - Input: jobDTO={}", "HTTP", "/api", "JobController", "createJob", jobDTO);
		String result = "";
		try {
			result = jobService.createJob(jobDTO);
		} catch (Exception e) {
			e.printStackTrace();;
		log.error("EXCEPTION: {}.{}() - Error: {} - Params: jobDTO={}", "JobController", "createJob", e.getMessage(), jobDTO, e);
		}
		log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "JobController", "createJob", durationMs, operationsPerformed);
		return result;
	}

	@PutMapping
	public void updateJob(@RequestBody JobDTO jobDTO)
	{
		log.info("API_ENTRY: {} {} - {}.{}() - Input: jobDTO={}", "HTTP", "/api", "JobController", "updateJob", jobDTO);
		String testValue = "Testing my local changes";
		testValue = "value changed, needs logging";
log.debug("CLASS_VAR: {}.{} - Changed: {} → {}", "JobController", "testValue", oldValue, testValue);

		jobService.updateJob(jobDTO);
	log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "JobController", "updateJob", durationMs, operationsPerformed);
	}

	@GetMapping(value = "/search")
	public List<JobDTO> search(@RequestParam String term, Pageable p)
	{
		log.info("API_ENTRY: {} {} - {}.{}() - Input: term={}, p={}", "GET", "/search", "JobController", "search", term, p);
		log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "JobController", "search", durationMs, operationsPerformed);
		return searchService.searchJobsByTerm(term, p);
	}

}

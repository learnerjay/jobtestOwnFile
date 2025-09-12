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

import com.jobportal.dto.EmployerDTO;
import com.jobportal.service.EmployerService;
import com.jobportal.service.SearchService;

@RestController
@RequestMapping("/employers")
@CrossOrigin("*")
public class EmployerController {
	
	@Autowired
	EmployerService employerService;
	
	@Autowired
	SearchService searchService;
	
	
	@GetMapping
	public List<EmployerDTO> findAll()
	{
		return employerService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public EmployerDTO findById(@PathVariable( "id" ) String id)
	{
		return employerService.findById(id);
	}
	
	@PostMapping
	public String createEmployer(@RequestBody EmployerDTO empDTO)
	{
		return employerService.createEmployer(empDTO);
	}
	
	@PutMapping
	public void updateEmployer(@RequestBody EmployerDTO empDTO)
	{
		employerService.updateEmployer(empDTO);
	}
 
}

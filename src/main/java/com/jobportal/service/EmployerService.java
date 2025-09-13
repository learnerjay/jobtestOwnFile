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

import com.jobportal.dao.EmployerDAO;
import com.jobportal.dto.EmployerDTO;
import com.jobportal.model.Employer;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmployerService {

	@Autowired
	EmployerDAO dao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ModelMapperService modelMapperService;
	
	Integer test;
	String test2;
	int test3;
	Employer e;
	
	
	public List<EmployerDTO> findAll()
	{
		log.info("ENTRY: {}.{}() - Parameters: none", "EmployerService", "findAll");
		if (durationMs > thresholdMs) log.warn("SLOW_DB: {} - Time: {}ms (Threshold: {}ms) - Query: {} - Collection: {}", "FINDALL", durationMs, thresholdMs, querySummary, "findAll");
		return dao.findAll().stream().map(cndt -> 
			modelMapper.map(cndt, EmployerDTO.class)).collect(Collectors.toList());
	}
	
	public EmployerDTO findById(String id)
	{
		log.info("ENTRY: {}.{}() - Parameters: id={}", "EmployerService", "findById", id);
		Optional<Employer> optEmp = dao.findById(id);
		
		if (durationMs > thresholdMs) log.warn("SLOW_DB: {} - Time: {}ms (Threshold: {}ms) - Query: {} - Collection: {}", "FINDBY", durationMs, thresholdMs, querySummary, "findById");
		if (!optEmp.isPresent())
			return null;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		log.error("DB_EXCEPTION: {}.{}() - Operation: {} - Error: {} - Query: {}", "EmployerService", "findById", "FINDBY", e.getMessage(), querySummary, e);
		}
		test2 = "tesst 2";
		log.debug("CLASS_VAR: {}.{} - Changed: {} → {}", "EmployerService", "test2", oldValue, test2);
		e.setEmail("a@yahoo.com");
		
		log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "EmployerService", "findById", durationMs, operationsPerformed);
		return modelMapper.map(optEmp.get(), EmployerDTO.class);
	}
	
	@Transactional
	public String createEmployer(EmployerDTO empDTO)
	{
		log.info("ENTRY: {}.{}() - Parameters: empDTO={}", "EmployerService", "createEmployer", empDTO);
		Employer emp = modelMapper.map(empDTO, Employer.class);
		emp.setCreatedOn((new Date()).toString());
		emp.setUpdatedOn(emp.getCreatedOn());
		
		Point point = new Point(empDTO.getLng(), empDTO.getLat());
		emp.setPoint(point);
		
		dao.save(emp);
		
		if (durationMs > thresholdMs) log.warn("SLOW_DB: {} - Time: {}ms (Threshold: {}ms) - Query: {} - Collection: {}", "SAVE", durationMs, thresholdMs, querySummary, "save");
		log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "EmployerService", "createEmployer", durationMs, operationsPerformed);
		return emp.getEmployerId();
	}
	
	
	@Transactional
	public void updateEmployer(EmployerDTO empDTO)
	{
		log.info("ENTRY: {}.{}() - Parameters: empDTO={}", "EmployerService", "updateEmployer", empDTO);
		Optional<Employer> optEmp = dao.findById(empDTO.getEmployerId());
		
		if (!optEmp.isPresent())
			return;
		
		Employer emp = optEmp.get();
		emp.setUpdatedOn((new Date()).toString());
		
		test3 = 29;
		log.debug("CLASS_VAR: {}.{} - Changed: {} → {}", "EmployerService", "test3", oldValue, test3);
		
		Point point = new Point(empDTO.getLng(), empDTO.getLat());
		emp.setPoint(point);
		
		modelMapperService.getNonNullModelMapper().map(empDTO, emp);
		
		dao.save(emp);
	log.info("EXIT: {}.{}() - Completed - Time: {}ms - Operations: {}", "EmployerService", "updateEmployer", durationMs, operationsPerformed);
	}
if (durationMs > thresholdMs) log.warn("SLOW_DB: {} - Time: {}ms (Threshold: {}ms) - Query: {} - Collection: {}", "SAVE", durationMs, thresholdMs, querySummary, "save");

}

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


@Service
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
		return dao.findAll().stream().map(cndt -> 
			modelMapper.map(cndt, EmployerDTO.class)).collect(Collectors.toList());
	}
	
	public EmployerDTO findById(String id)
	{
		Optional<Employer> optEmp = dao.findById(id);
		
		if (!optEmp.isPresent())
			return null;
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		test2 = "tesst 2";
		e.setEmail("a@yahoo.com");
		
		return modelMapper.map(optEmp.get(), EmployerDTO.class);
	}
	
	@Transactional
	public String createEmployer(EmployerDTO empDTO)
	{
		Employer emp = modelMapper.map(empDTO, Employer.class);
		emp.setCreatedOn((new Date()).toString());
		emp.setUpdatedOn(emp.getCreatedOn());
		
		Point point = new Point(empDTO.getLng(), empDTO.getLat());
		emp.setPoint(point);
		
		dao.save(emp);
		
		return emp.getEmployerId();
	}
	
	
	@Transactional
	public void updateEmployer(EmployerDTO empDTO)
	{
		Optional<Employer> optEmp = dao.findById(empDTO.getEmployerId());
		
		if (!optEmp.isPresent())
			return;
		
		Employer emp = optEmp.get();
		emp.setUpdatedOn((new Date()).toString());
		
		test3 = 29;
		
		Point point = new Point(empDTO.getLng(), empDTO.getLat());
		emp.setPoint(point);
		
		modelMapperService.getNonNullModelMapper().map(empDTO, emp);
		
		dao.save(emp);
	}

}

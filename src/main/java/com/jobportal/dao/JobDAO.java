package com.jobportal.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Job;

@Repository
public interface JobDAO extends MongoRepository<Job, String> {
	
	public List<Job> findByContactEmail(String contactEmail);

}

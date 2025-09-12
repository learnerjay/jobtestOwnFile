package com.jobportal.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Employer;

@Repository
public interface EmployerDAO extends MongoRepository<Employer, String> {

}

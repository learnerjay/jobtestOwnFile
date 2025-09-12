package com.jobportal.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Candidate;

@Repository
public interface CandidateDAO extends MongoRepository<Candidate, String> {

}

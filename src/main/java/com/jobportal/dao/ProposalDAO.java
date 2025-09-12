package com.jobportal.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.model.Proposal;

@Repository
public interface ProposalDAO extends MongoRepository<Proposal, String> {
	
	public List<Proposal> findByJobId(String jobId);

}

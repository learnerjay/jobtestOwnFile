package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jobportal.client.LocationClient;
import com.jobportal.dto.LocationDTO;

@Service
public class LocationService {

	@Autowired
	private LocationClient locationClient;
	
	@Autowired
	JobService jobService;
	
	@Async
	public void findByAddress(String jobId, String address)
	{
		LocationDTO location = locationClient.findByAddress(address);
		
		jobService.updateLocation(jobId, location);
	}
}

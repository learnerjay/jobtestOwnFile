package com.jobportal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobportal.dto.LocationDTO;

@FeignClient(name = "locationClient", url = "https://maps.googleapis.com/maps/api/geocode/json?key=${google.maps.apiKey}")
public interface LocationClient {
	
	@GetMapping
    LocationDTO findByAddress(@RequestParam String address);

}

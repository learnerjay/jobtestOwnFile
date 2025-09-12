package com.jobportal.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

	
	public ModelMapper getNonNullModelMapper()
	{
		ModelMapper mm = new ModelMapper();
		
		mm.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return mm;
	}
}

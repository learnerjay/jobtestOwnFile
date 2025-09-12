package com.jobportal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;


@Repository
public class SearchDAO {
	
	@Autowired
	MongoTemplate template;

	public <T> List<T> searchByTerm(Class className, String term, Pageable p)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				.matchingAny(term.split("\\s+"));  // handles multiple words

		Query query = TextQuery.queryText(criteria)
				.with(Sort.by(Sort.Direction.DESC, "updatedOn"))
				.with(p);

		List<T> result = template.find(query, className);

		return result;
	}
}

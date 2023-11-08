package com.data.tools.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.data.tools.api.entity.DbConfiguration;

public interface DbConfigurationService {
	
	Page<DbConfiguration> getAllDbConfigurations(Pageable page);
	
	DbConfiguration getDbConfigurationById(Long id);
	
	void deleteDbConfigurationById(Long id);

	DbConfiguration saveDbConfigurationDetails(DbConfiguration DbConfiguration);
	
	DbConfiguration updateDbConfigurationDetails(Long id, DbConfiguration DbConfiguration);
	
	List<DbConfiguration> readByCategory(String category, Pageable page);
	
	List<DbConfiguration> readByName(String keyword, Pageable page); 
}

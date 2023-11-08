package com.data.tools.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.data.tools.api.entity.DbConfiguration;
import com.data.tools.api.exceptions.ResourceNotFoundException;
import com.data.tools.api.repository.DbConfigurationRepository;

@Service
public class DbConfigurationServiceImpl implements DbConfigurationService {

	@Autowired
	private DbConfigurationRepository DbConfigurationRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Page<DbConfiguration> getAllDbConfigurations(Pageable page) {
		return DbConfigurationRepo.findByUserId(userService.getLoggedInUser().getId(), page);
	}

	@Override
	public DbConfiguration getDbConfigurationById(Long id){
		Optional<DbConfiguration> DbConfiguration = DbConfigurationRepo.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
		if (DbConfiguration.isPresent()) {
			return DbConfiguration.get();
		}
		throw new ResourceNotFoundException("DbConfiguration is not found for the id "+id);
	}

	@Override
	public void deleteDbConfigurationById(Long id) {
		DbConfiguration DbConfiguration = getDbConfigurationById(id);
		DbConfigurationRepo.delete(DbConfiguration);
	}

	@Override
	public DbConfiguration saveDbConfigurationDetails(DbConfiguration DbConfiguration) {
		DbConfiguration.setUser(userService.getLoggedInUser());
		return DbConfigurationRepo.save(DbConfiguration);
	}

	@Override
	public DbConfiguration updateDbConfigurationDetails(Long id, DbConfiguration DbConfiguration){
		DbConfiguration existingDbConfiguration = getDbConfigurationById(id);
		existingDbConfiguration.setName(DbConfiguration.getName() != null ? DbConfiguration.getName() : existingDbConfiguration.getName());
		existingDbConfiguration.setDescription(DbConfiguration.getDescription() != null ? DbConfiguration.getDescription() : existingDbConfiguration.getDescription());
		existingDbConfiguration.setCategory(DbConfiguration.getCategory() != null ? DbConfiguration.getCategory() : existingDbConfiguration.getCategory());		
		existingDbConfiguration.setIpAddress(DbConfiguration.getIpAddress() != null ? DbConfiguration.getIpAddress() : existingDbConfiguration.getIpAddress());
		return DbConfigurationRepo.save(existingDbConfiguration);
	}

	@Override
	public List<DbConfiguration> readByCategory(String category, Pageable page) {
		return DbConfigurationRepo.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
	}

	@Override
	public List<DbConfiguration> readByName(String keyword, Pageable page) {
		return DbConfigurationRepo.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
	}

}




























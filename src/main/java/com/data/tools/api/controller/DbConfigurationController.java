package com.data.tools.api.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.data.tools.api.entity.DbConfiguration;
import com.data.tools.api.service.DbConfigurationService;
import jakarta.validation.Valid;

@RestController
public class DbConfigurationController {

	@Autowired
	private DbConfigurationService DbConfigurationService;
	
	@GetMapping("/DbConfigurations")
	public List<DbConfiguration> getAllDbConfigurations(Pageable page) {
		return DbConfigurationService.getAllDbConfigurations(page).toList();
	}
	
	@GetMapping("/DbConfigurations/{id}")
	public DbConfiguration getDbConfigurationById(@PathVariable Long id){
		return DbConfigurationService.getDbConfigurationById(id);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/DbConfigurations")
	public void deleteDbConfigurationById(@RequestParam Long id) {
		DbConfigurationService.deleteDbConfigurationById(id);
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/DbConfigurations")
	public DbConfiguration saveDbConfigurationDetails(@Valid @RequestBody DbConfiguration DbConfiguration) {
		return DbConfigurationService.saveDbConfigurationDetails(DbConfiguration);
	}
	
	@PutMapping("/DbConfigurations/{id}")
	public DbConfiguration updateDbConfigurationDetails(@RequestBody DbConfiguration DbConfiguration, @PathVariable Long id){
		return DbConfigurationService.updateDbConfigurationDetails(id, DbConfiguration);
	}
	
	@GetMapping("/DbConfigurations/category")
	public List<DbConfiguration> getDbConfigurationsByCategory(@RequestParam String category, Pageable page) {
		return DbConfigurationService.readByCategory(category, page);
	}
	
	@GetMapping("/DbConfigurations/name")
	public List<DbConfiguration> getDbConfigurationsByName(@RequestParam String keyword, Pageable page) {
		return DbConfigurationService.readByName(keyword, page);
	}
}























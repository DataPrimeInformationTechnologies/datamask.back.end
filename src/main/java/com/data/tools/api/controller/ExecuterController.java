package com.data.tools.api.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.tools.api.service.ExecutionService;

@RestController
public class ExecuterController {

	@Autowired
	private ExecutionService executionService;
	
	@GetMapping("/runsql")
	public List<Object> getAllDbConfigurations(Pageable page) {
		return executionService.runSQLCommand();
	}
}























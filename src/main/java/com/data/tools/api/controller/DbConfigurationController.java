package com.data.tools.api.controller;

import com.data.tools.api.entity.DbConfiguration;
import com.data.tools.api.service.DbConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/db-conf")
public class DbConfigurationController {

    private final DbConfigurationService dbConfigurationService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DbConfiguration>> getAllConfiguration() {
        List<DbConfiguration> configurations= dbConfigurationService.getAllDbConfigurations();
        return ResponseEntity.ok(configurations);
    }

    @GetMapping("/get/{confId}")
    public ResponseEntity<DbConfiguration> getDbConfigurationById(@PathVariable Long confId) {
       DbConfiguration configuration = dbConfigurationService.getDbConfigurationById(confId);
        return ResponseEntity.ok(configuration);
    }

    @PostMapping("/add")
    public ResponseEntity<DbConfiguration> addDbConfiguration(@RequestBody DbConfiguration dbc) {
        DbConfiguration configuration = dbConfigurationService.addConfiguration(dbc);
        return ResponseEntity.ok(configuration);
    }

    @DeleteMapping("/delete/{confId}")
    public ResponseEntity<String>  deleteDbConfiguration(@PathVariable Long confId) {
        dbConfigurationService.deleteDbConfigurationById(confId);
        return ResponseEntity.ok("Configuration with ID "+confId+"has been deleted.");
    }

    @PutMapping("/update/{confId}")
    public ResponseEntity<DbConfiguration> updateDbConfiguration(@PathVariable Long confId, @RequestBody DbConfiguration updateConfig) {
        DbConfiguration configuration = dbConfigurationService.updateDbConfiguration(confId,updateConfig);
        return ResponseEntity.ok(configuration);
    }

}























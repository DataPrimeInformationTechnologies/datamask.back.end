package com.data.tools.api.controller;
import com.data.tools.api.entity.DBConfiguration2;
import com.data.tools.api.service.DBConfigurationService2;
import com.data.tools.api.service.DBConnectionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class DBController {

    @Autowired
    private DBConfigurationService2 dbService;
    @Autowired
    private DBConnectionTestService connectionTestService;

    // Test DB Connection
    @GetMapping("/{id}/testConnection")
    public ResponseEntity<String> testDBConnection(@PathVariable Long id) {
        String result = connectionTestService.testDBConnection(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Post config
    @PostMapping
    public DBConfiguration2 createConfig(@RequestBody DBConfiguration2 dbConfig) {
        return dbService.createConfig(dbConfig);
    }

    // Get all config
    @GetMapping()
    public List<DBConfiguration2> getDBConfig(){

        return dbService.getDBConfig();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DBConfiguration2> getDBConfigById(@PathVariable Long id) {
        DBConfiguration2 config = dbService.getDBConfigById(id);
        if (config != null) {
            return new ResponseEntity<>(config, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete all config
    @DeleteMapping
    public String deleteAllConfig() {
        dbService.deleteAllConfig();
        return "All configuration information  have been deleted successfully.";
    }

    // Delete a config
    @DeleteMapping("/{id}")
    public void deleteConfig(@PathVariable Long id){
        dbService.deleteConfig(id);}

    // Update a config
    @PutMapping("/{id}")
    public DBConfiguration2 updateConfig(@PathVariable Long id, @RequestBody DBConfiguration2 configDetails){
        return dbService.updateConfig(id, configDetails);
    }

}
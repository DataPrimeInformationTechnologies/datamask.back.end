package com.springpostgre.project.config;

import com.springpostgre.project.config.DBConfiguration;
import com.springpostgre.project.config.DBConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class DBController {

    @Autowired
    private DBConfigurationService dbService;


    // Post config
    @PostMapping
    public DBConfiguration createConfig(@RequestBody DBConfiguration dbConfig) {
        return dbService.createConfig(dbConfig);
    }

    // Get all config
    @GetMapping()
    public List<DBConfiguration> getDBConfig(){

        return dbService.getDBConfig();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DBConfiguration> getDBConfigById(@PathVariable Long id) {
        DBConfiguration config = dbService.getDBConfigById(id);
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
    public DBConfiguration updateConfig(@PathVariable Long id, @RequestBody DBConfiguration configDetails){
        return dbService.updateConfig(id, configDetails);
    }

}

package com.springpostgre.project.config;

import com.springpostgre.project.config.DBConfiguration;
import com.springpostgre.project.config.DBConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class DBController {

    @Autowired
    private DBConfigurationService dbService;


    // Post config
    @PostMapping
    public DBConfiguration createConfig(@RequestBody DBConfiguration dbConfig){ return dbService.createConfig(dbConfig);}

    // Get all config
    @GetMapping
    public List<DBConfiguration> getDBConfig(){
        return dbService.getDBConfig();
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

    @PutMapping("/{id}")
    public DBConfiguration updateConfig(@PathVariable Long id, DBConfiguration configDetails){
        return dbService.updateConfig(id, configDetails);
    }

}

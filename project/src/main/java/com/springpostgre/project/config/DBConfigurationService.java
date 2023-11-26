package com.springpostgre.project.config;

import com.springpostgre.project.config.DBConfiguration;
import com.springpostgre.project.config.DBConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBConfigurationService {

    @Autowired
    private DBConfigurationRepository dbConfigurationRepository;

    //Create config
    public DBConfiguration createConfig(DBConfiguration db_config ){ return dbConfigurationRepository.save(db_config); }

    //List config
    public List<DBConfiguration> getDBConfig() {return dbConfigurationRepository.findAll(); }

    //Delete all config
    public void deleteAllConfig() {dbConfigurationRepository.deleteAll();}

    //Delete a config
    public void deleteConfig(Long id){ dbConfigurationRepository.deleteById(id);}

    public DBConfiguration updateConfig(Long id, DBConfiguration configDetails) {
        Optional<DBConfiguration> dbConfiguration = dbConfigurationRepository.findById(id);
        if (dbConfiguration.isPresent()) {
            DBConfiguration existingConfig = dbConfiguration.get();
            existingConfig.setHost(configDetails.getHost());
            existingConfig.setPort(configDetails.getPort());
            existingConfig.setPassword(configDetails.getPassword());
            existingConfig.setUsername(configDetails.getUsername());
            existingConfig.setRole(configDetails.getRole());
            existingConfig.setDatabase(configDetails.getDatabase());
            existingConfig.setConnName(configDetails.getConnName());
            return dbConfigurationRepository.save(existingConfig);
        }
        else {
            System.out.println("NOT FOUND !!");
        }
    return null;
    }
}

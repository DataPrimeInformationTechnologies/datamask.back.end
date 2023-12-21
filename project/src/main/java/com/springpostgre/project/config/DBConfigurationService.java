package com.springpostgre.project.config;

import com.springpostgre.project.exceptions.DBAlreadyExistException;
import com.springpostgre.project.exceptions.DBNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBConfigurationService {

    @Autowired
    private DBConfigurationRepository dbConfigurationRepository;

    //Create config
    public DBConfiguration createConfig(DBConfiguration db_config ){
        var existingConfig = dbConfigurationRepository.findById(db_config.getId()).orElse(null);
        if(existingConfig != null){
            throw new DBAlreadyExistException("DB Configuration already exist for the given id.");
        }
        return dbConfigurationRepository.save(db_config);
    }

    //List config
    public List<DBConfiguration> getDBConfig() {
        return dbConfigurationRepository.findAll();
    }

    public DBConfiguration getDBConfigById(Long id) {
        return dbConfigurationRepository.findById(id)
                .orElseThrow(() -> new DBNotFoundException("Config not found "));
    }



    //Delete all config
    public void deleteAllConfig() {dbConfigurationRepository.deleteAll();}

    //Delete a config
    public void deleteConfig(Long id){
        var config = dbConfigurationRepository.findById(id).orElse(null);
        if(config == null){
            throw new DBNotFoundException("Config not found");
        }
        dbConfigurationRepository.deleteById(id);
    }

    public DBConfiguration updateConfig(Long id, DBConfiguration configDetails) {
        Optional<DBConfiguration> dbConfiguration = dbConfigurationRepository.findById(id);
        if (dbConfiguration.isPresent()) {
            DBConfiguration existingConfig = dbConfiguration.get();
            existingConfig.setHost(configDetails.getHost());
            existingConfig.setPort(configDetails.getPort());
            existingConfig.setPassword(configDetails.getPassword());
            existingConfig.setUsername(configDetails.getUsername());
            existingConfig.setRole(configDetails.getRole());
            existingConfig.setSid(configDetails.getSid());
            existingConfig.setConnection(configDetails.getConnection());
            return dbConfigurationRepository.save(existingConfig);
        }
        else {
            throw new DBNotFoundException("Config not found");

        }
    }
}

package com.data.tools.api.service;

import com.data.tools.api.entity.DBConfiguration2;
import com.data.tools.api.entity.DBConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.data.tools.api.exceptions.DBAlreadyExistException;
import com.data.tools.api.exceptions.DBNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//SERVICE 2
import java.util.List;
import java.util.Optional;

@Service
public class DBConfigurationService2 {

    @Autowired
    private DBConfigurationRepository dbConfigurationRepository;

    //Create config
    public DBConfiguration2 createConfig(DBConfiguration2 db_config ){
        var existingConfig = dbConfigurationRepository.findById(db_config.getId()).orElse(null);
        if(existingConfig != null){
            throw new DBAlreadyExistException("DB Configuration already exist for the given id.");
        }
        return dbConfigurationRepository.save(db_config);
    }

    //List config
    public List<DBConfiguration2> getDBConfig() {
        return dbConfigurationRepository.findAll();
    }

    public DBConfiguration2 getDBConfigById(Long id) {
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

    public DBConfiguration2 updateConfig(Long id, DBConfiguration2 configDetails) {
        Optional<DBConfiguration2> dbConfiguration = dbConfigurationRepository.findById(id);
        if (dbConfiguration.isPresent()) {
            DBConfiguration2 existingConfig = dbConfiguration.get();
            existingConfig.setHost(configDetails.getHost());
            existingConfig.setPort(configDetails.getPort());
            existingConfig.setPassword(configDetails.getPassword());
            existingConfig.setUsername(configDetails.getUsername());
            existingConfig.setRole(configDetails.getRole());
            existingConfig.setServiceName(configDetails.getServiceName());
            existingConfig.setConnectionName(configDetails.getConnectionName());
            return dbConfigurationRepository.save(existingConfig);
        }
        else {
            throw new DBNotFoundException("Config not found");

        }
    }
}
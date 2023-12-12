package com.data.tools.api.service;

import com.data.tools.api.entity.DbConfiguration;
import com.data.tools.api.entity.DbMigration;
import com.data.tools.api.exceptions.Exceptions;
import com.data.tools.api.exceptions.GlobalException;
import com.data.tools.api.repository.DbConfigurationRepository;
import com.data.tools.api.repository.DbMigrationRepository;
import com.data.tools.api.util.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Service
public class DbConnectionService {

    private final DbConfigurationRepository dbConfigurationRepository;
    private final DbMigrationRepository dbMigrationRepository;

    public DbConnectionService(DbConfigurationRepository dbConfigurationRepository, DbMigrationRepository dbMigrationRepository) {
        this.dbConfigurationRepository = dbConfigurationRepository;
        this.dbMigrationRepository = dbMigrationRepository;
    }


    public Connection connectToDb(DbConfiguration dbConf) {
        try {
            log.debug("Trying to open a new connection");
            Connection connection = DriverManager.getConnection(dbConf.getUrl(), dbConf.getUserName(), dbConf.getPassword());
            loadDriver();
            log.debug("Connection opened");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw GlobalException.throwEx(Exceptions.INTERVAL_EXCEPTION, e.getMessage());
        }
    }

    private void loadDriver()  {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw GlobalException.throwEx(Exceptions.INTERVAL_EXCEPTION, e.getMessage());
        }
    }

    public Connection getConnectionById(Long id) {
        DbConfiguration dbConf = this.dbConfigurationRepository.findById(id)
                .orElseThrow(() -> GlobalException.throwEx(Exceptions.OBJECT_NOT_FOUND, "DBConfiguration not found by Id"));
        return connectToDb(dbConf);
    }


}

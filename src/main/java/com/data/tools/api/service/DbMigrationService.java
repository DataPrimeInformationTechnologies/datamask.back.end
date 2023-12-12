package com.data.tools.api.service;

import com.data.tools.api.dto.MigrationConfig;
import com.data.tools.api.entity.DbConfiguration;
import com.data.tools.api.entity.DbMigration;
import com.data.tools.api.exceptions.Exceptions;
import com.data.tools.api.exceptions.GlobalException;
import com.data.tools.api.repository.DbConfigurationRepository;
import com.data.tools.api.repository.DbMigrationRepository;
import com.data.tools.api.util.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
@RequiredArgsConstructor
public class DbMigrationService {

    private final DbMigrationRepository dbMigrationRepository;
    private final DbConfigurationRepository dbConfigurationRepository;

    private final DbConnectionService dbConnectionService;

    public void setConfig(MigrationConfig config) {
        DbConfiguration source = this.dbConfigurationRepository.findById(config.sourceId()).orElseThrow();
        DbConfiguration target = this.dbConfigurationRepository.findById(config.targetId()).orElseThrow();
        DbMigration dbMigration;
        if (config.id() != null) {
            dbMigration = this.dbMigrationRepository.findById(config.id())
                   .orElseThrow(() -> GlobalException.throwEx(Exceptions.OBJECT_NOT_FOUND_BY_ID, "DBMigration not found by id"));
        } else {
        dbMigration = new DbMigration();
        }
        dbMigration.setConfigToEntity(config.name(), config.desc(), source, target);
        this.dbMigrationRepository.save(dbMigration);
    }


    public void migrate() throws SQLException {


    }


    private DbMigration getDbMigrationByUser() {
        return this.dbMigrationRepository.findByUserAndSelectedTrue(UserHelper.getUser())
                .orElseThrow(() -> GlobalException.throwEx(Exceptions.OBJECT_NOT_FOUND, "DBMigration not found"));

    }


}

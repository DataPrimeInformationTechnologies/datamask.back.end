package com.data.tools.api.service;

import com.data.tools.api.dao.DbConnectionWithData;
import com.data.tools.api.entity.DbConnection;

import java.util.List;

public interface DbConnectionService {

    List<DbConnection> getAllDbConfiguration();
    DbConnection getDbConfigurationById(Long connectionId);
    DbConnection addDbConfiguration(DbConnection dbConnection, Long userId);
    void deleteDbConfigurationById(Long connectionId);
    DbConnection updateDbConfiguration(Long connectionId, DbConnection updateConfig);
    DbConnectionWithData findSchemas(Long conId);
    DbConnectionWithData findTablesBySchemaName(Long conId,String schemaName);
    DbConnectionWithData findColumnsByTable(Long conId,String schemaName, String tableName);
}

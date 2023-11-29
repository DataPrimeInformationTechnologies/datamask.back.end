package com.data.tools.api.service;

import com.data.tools.api.entity.ConnectionInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OracleConnectionService {
    Connection getConnection(ConnectionInfo connectionInfo) throws SQLException;

    List<String> getSchemas(Connection connection) throws SQLException;

    List<String> getTables(Connection connection, String schemaName) throws SQLException;

    List<String> getColumns(Connection connection, String schemaName, String tableName) throws SQLException;
}

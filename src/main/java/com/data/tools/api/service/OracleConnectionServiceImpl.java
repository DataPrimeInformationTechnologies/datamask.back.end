package com.data.tools.api.service;

import com.data.tools.api.entity.ConnectionInfo;
import com.data.tools.api.entity.DatabaseInfo;
import com.data.tools.api.repository.DatabaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class OracleConnectionServiceImpl implements OracleConnectionService{

    @Autowired
    private DatabaseInfoRepository databaseInfoRepository;
    @Override
    public Connection getConnection(ConnectionInfo connectionInfo) throws SQLException {
        return DriverManager.getConnection(
                connectionInfo.getUrl(),
                connectionInfo.getUsername(),
                connectionInfo.getPassword()
        );
    }

    @Override
    public List<String> getSchemas(Connection connection) throws SQLException {
        List<String> schemas = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT OWNER FROM ALL_TABLES")) {

            while (resultSet.next()) {
                schemas.add(resultSet.getString("OWNER"));
            }
        }
        return schemas;
    }

    @Override
    public List<String> getTables(Connection connection, String schemaName) throws SQLException {
        List<String> tables = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME FROM ALL_TABLES WHERE OWNER = '" + schemaName + "'")) {

            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }
        }
        return tables;
    }

    @Override
    public List<String> getColumns(Connection connection, String schemaName, String tableName) throws SQLException {
        List<String> columns = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE OWNER = '" + schemaName + "' AND TABLE_NAME = '" + tableName + "'")) {

            while (resultSet.next()) {
                columns.add(resultSet.getString("COLUMN_NAME"));
            }
        }
        return columns;
    }
    /*
    public List<DatabaseInfo> getDatabaseInfo(String schemaName, String tableName) {
        if (tableName == null) {
            return databaseInfoRepository.findBySchemaName(schemaName);
        } else {
            return databaseInfoRepository.findBySchemaNameAndTableName(schemaName, tableName);
        }
    }*/
}

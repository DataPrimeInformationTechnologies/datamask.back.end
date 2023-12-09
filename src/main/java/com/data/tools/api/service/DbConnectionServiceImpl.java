package com.data.tools.api.service;

import com.data.tools.api.dao.DbConnectionWithData;
import com.data.tools.api.entity.DbConnection;
import com.data.tools.api.entity.User;
import com.data.tools.api.repository.DbConnectionRepository;
import com.data.tools.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbConnectionServiceImpl implements DbConnectionService {

    @Autowired
    private DbConnectionRepository dbConnectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DbConnection> getAllDbConfiguration() {
        return dbConnectionRepository.findAll();
    }

    @Override
    public DbConnection getDbConfigurationById(Long connectionId) {
        return dbConnectionRepository.findById(connectionId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + connectionId));
    }

    @Override
    public DbConnection addDbConfiguration(DbConnection dbConnection, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        dbConnection.setUser(user);
        return dbConnectionRepository.save(dbConnection);
    }

    @Override
    public void deleteDbConfigurationById(Long connectionId) {
        dbConnectionRepository.deleteById(connectionId);
    }

    @Override
    public DbConnection updateDbConfiguration(Long connectionId, DbConnection updateConfig) {
        DbConnection existingDbConnection = dbConnectionRepository.findById(connectionId)
                .orElseThrow(() -> new EntityNotFoundException("Db connection not found with id: " + connectionId));
        existingDbConnection.setUrl(updateConfig.getUrl());
        existingDbConnection.setConnectionName(updateConfig.getConnectionName());
        existingDbConnection.setUserName(updateConfig.getUserName());
        existingDbConnection.setPassword(updateConfig.getPassword());
        existingDbConnection.setUser(updateConfig.getUser());

        return dbConnectionRepository.save(existingDbConnection);
    }

    @Override
    public DbConnectionWithData findSchemas(Long conId) {
        DbConnection dbConf = dbConnectionRepository.findById(conId)
                .orElseThrow(() -> new EntityNotFoundException("Connection not found with id: " + conId));
        List<String> schemas = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(dbConf.getUrl(),dbConf.getUserName(), dbConf.getPassword())) {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Bu olmaması lazım.
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet resultSet = metaData.getSchemas();

            while (resultSet.next()) {
                String schemaName = resultSet.getString("TABLE_SCHEM");
                schemas.add(schemaName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        DbConnectionWithData data = new DbConnectionWithData();
        data.setConn_id(conId);
        data.setSchemas(schemas);
        return data;
    }

    @Override
    public DbConnectionWithData findTablesBySchemaName(Long conId,String schemaName) {
        DbConnection dbConf = dbConnectionRepository.findById(conId)
                .orElseThrow(() -> new EntityNotFoundException("Connection not found with id: " + conId));
        List<String> tables = new ArrayList<>();
        DbConnectionWithData schemas = findSchemas(conId);
        try(Connection con = DriverManager.getConnection(dbConf.getUrl(),dbConf.getUserName(), dbConf.getPassword())) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet resultSet = metaData.getTables(null,schemaName,null,new String[]{"TABLE"});

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tables.add(tableName);
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        DbConnectionWithData data = new DbConnectionWithData();
        data.setConn_id(conId);
        data.setSchemas(schemas.getSchemas());
        data.setSelectedSchema(schemaName);
        data.setTableNames(tables);
        return data;
    }

    @Override
    public DbConnectionWithData findColumnsByTable(Long conId,String schemaName, String tableName) {
        DbConnection dbConf = dbConnectionRepository.findById(conId)
                .orElseThrow(() -> new EntityNotFoundException("Connection not found with id: " + conId));

        List<String> columns = new ArrayList<>();
        DbConnectionWithData schemasAndTables = findTablesBySchemaName(conId,schemaName);
        try (Connection con = DriverManager.getConnection(dbConf.getUrl(), dbConf.getUserName(), dbConf.getPassword())) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, schemaName, tableName, null);

            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                columns.add(columnName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        DbConnectionWithData data = new DbConnectionWithData();
        data.setConn_id(conId);
        data.setSchemas(schemasAndTables.getSchemas());
        data.setSelectedSchema(schemasAndTables.getSelectedSchema());
        data.setTableNames(schemasAndTables.getTableNames());
        data.setSelectedTable(tableName);
        data.setColumnNames(columns);
        return data;
    }
}

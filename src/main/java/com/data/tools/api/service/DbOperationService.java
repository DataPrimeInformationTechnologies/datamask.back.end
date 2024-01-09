package com.data.tools.api.service;

import com.data.tools.api.constants.SQLConst;
import com.data.tools.api.dto.TableInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DbOperationService {

    private final DbConnectionService dbConnectionService;

    public List getDataByTable(Long dbConfigId, String schema, String tableName) throws SQLException {
        Connection connection = dbConnectionService.getConnectionById(dbConfigId);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLConst.getSelectBySchemeAndTable(schema, tableName));
        List<Map<String, Object>> result = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> resMap = new HashMap<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                resMap.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
            }
            result.add(resMap);
        }
        return result;
    }

    public List getSchema(Long dbConfigId) throws SQLException {
        Connection connection = dbConnectionService.getConnectionById(dbConfigId);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLConst.getSchemas());

        List<String> result = new ArrayList<>();
        while(resultSet.next()) {
            result.add(resultSet.getString("Schemas"));
        }
        return result;
    }

    public  List getTablesBySelectedSchema(Long dbConfigId,String schemaName) throws SQLException {
        Connection connection = dbConnectionService.getConnectionById(dbConfigId);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLConst.getTablesBySchema(schemaName));

        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getString("Tables"));
        }
        return result;
    }

    public List<TableInfo> getColumnsAndTypeBySelectedTable(Long dbConfigId, String schemaName, String tableName) throws SQLException {
        Connection connection = dbConnectionService.getConnectionById(dbConfigId);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLConst.getColumnsByTable(schemaName,tableName));

        List<TableInfo> result = new ArrayList<>();
        while(resultSet.next()){
            TableInfo columnDTO =new TableInfo(
                    resultSet.getString("COLUMN_NAME"),
                    resultSet.getString("DATA_TYPE"),
                    resultSet.getInt("DATA_LENGTH"),
                    resultSet.getString("NULLABLE"),
                    resultSet.getString("CONSTRAINTS")
            );
            result.add(columnDTO);
        }
        return result;
    }

}

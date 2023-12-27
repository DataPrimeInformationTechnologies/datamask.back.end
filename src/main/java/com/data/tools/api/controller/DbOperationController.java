package com.data.tools.api.controller;

import com.data.tools.api.dto.TableInfo;
import com.data.tools.api.service.DbOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/db-op")
public class DbOperationController {

    public final DbOperationService dbOperationService;
    @GetMapping("/getSchema/{dbConfigId}")
    public ResponseEntity<List<String>> getSchema(@PathVariable Long dbConfigId) throws SQLException {
        List<String> result = dbOperationService.getSchema(dbConfigId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getTable")
    public ResponseEntity<List<String>> getTableBySelectedSchema(@Param("dbConfigId") Long dbConfigId,@Param("schemaName") String schemaName) throws SQLException {
        List<String> result = dbOperationService.getTablesBySelectedSchema(dbConfigId,schemaName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getColumns")
    public ResponseEntity<List<TableInfo>>getColumnsAndDataType(@Param("dbConfigId") Long dbConfigId, @Param("schemaName") String schemaName, @Param("tableName") String tableName) throws SQLException {
        List<TableInfo> result = dbOperationService.getColumnsAndTypeBySelectedTable(dbConfigId,schemaName,tableName);
        return ResponseEntity.ok(result);
    }

}

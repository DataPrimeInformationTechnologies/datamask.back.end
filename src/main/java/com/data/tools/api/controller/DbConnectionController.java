package com.data.tools.api.controller;

import com.data.tools.api.dao.DbConnectionWithData;
import com.data.tools.api.entity.DbConnection;
import com.data.tools.api.service.DbConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/db-con")
public class DbConnectionController {

    private final DbConnectionService dbConfigurationService;


    @GetMapping("/getAll")
    public ResponseEntity<List<DbConnection>> getAllConfigurations() {
        List<DbConnection> dbConnections = dbConfigurationService.getAllDbConfiguration();
        return  ResponseEntity.ok(dbConnections);
    }

    @GetMapping("/get/{connectionId}")
    public ResponseEntity<DbConnection> getDbConfigurationById(@PathVariable Long connectionId) {
        DbConnection dbConnection = dbConfigurationService.getDbConfigurationById(connectionId);
        return ResponseEntity.ok(dbConnection);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<DbConnection> addDbConfiguration(@PathVariable Long userId, @RequestBody DbConnection dbc) {
        DbConnection dbConnection = dbConfigurationService.addDbConfiguration(dbc,userId);
        return ResponseEntity.ok(dbConnection);
    }

    @DeleteMapping("/delete/{connectionId}")
    public ResponseEntity<String>  deleteDbConfiguration(@PathVariable Long connectionId) {
        dbConfigurationService.deleteDbConfigurationById(connectionId);
        return ResponseEntity.ok("Configuration with ID "+connectionId+"has been deleted.");
    }

    @PutMapping("/update/{connectionId}")
    public ResponseEntity<DbConnection> updateDbConfiguration(@PathVariable Long connectionId, @RequestBody DbConnection dbConnection) {
        DbConnection existingConf = dbConfigurationService.updateDbConfiguration(connectionId, dbConnection);
        return ResponseEntity.ok(existingConf);
    }

    @GetMapping("/schemas/{conId}")
    public ResponseEntity<DbConnectionWithData> getSchemasByConnectionId(@PathVariable("conId") Long conId) {
        DbConnectionWithData schemas = dbConfigurationService.findSchemas(conId);
        return  new ResponseEntity<>(schemas, HttpStatus.OK);
    }

    @GetMapping("/tables/{conId}")
    public ResponseEntity<DbConnectionWithData> getTablesBySchemaName(@PathVariable("conId") Long conId, @Param("schemaName") String schemaName) {
        DbConnectionWithData tables = dbConfigurationService.findTablesBySchemaName(conId,schemaName);
        return new ResponseEntity<>(tables,HttpStatus.OK);
    }

    @GetMapping("/columns/{conId}")
    public  ResponseEntity<DbConnectionWithData> getColumnsByTableName(@PathVariable("conId") Long conId,@Param("schemaName") String schemaName,@Param("tableName") String tableName) {
        DbConnectionWithData columns = dbConfigurationService.findColumnsByTable(conId,schemaName,tableName);
        return  new ResponseEntity<>(columns,HttpStatus.OK);
    }
}
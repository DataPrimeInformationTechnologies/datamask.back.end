package com.data.tools.api.controller;

import com.data.tools.api.entity.ConnectionInfo;
import com.data.tools.api.service.OracleConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/oracle")
public class OracleController {


    @Autowired
    private OracleConnectionService oracleConnectionService;
    @GetMapping("/deneme")
    private String getText()
    {
        return  "Deneme";
    }
    @GetMapping("/schemas")
    private List<String> getSchemas(@RequestParam String url,@RequestParam String username,@RequestParam String password)
    {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setUrl(url);
        connectionInfo.setUsername(username);
        connectionInfo.setPassword(password);

        try (Connection connection = oracleConnectionService.getConnection(connectionInfo)) {
            return oracleConnectionService.getSchemas(connection);
        } catch (SQLException e) {

            e.printStackTrace();
            return List.of();
        }
    }

    @GetMapping("/table/{schemaName}")
    public List<String> getTables(@PathVariable String schemaName, @RequestParam String url, @RequestParam String username, @RequestParam String password)
    {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setUrl(url);
        connectionInfo.setUsername(username);
        connectionInfo.setPassword(password);

        try (Connection connection = oracleConnectionService.getConnection(connectionInfo)) {
            return oracleConnectionService.getTables(connection, schemaName);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }
    @GetMapping("/columns/{schemaName}/{tableName}")
    public List<String> getColumns(@PathVariable String schemaName, @PathVariable String tableName, @RequestParam String url, @RequestParam String username, @RequestParam String password)
    {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setUrl(url);
        connectionInfo.setUsername(username);
        connectionInfo.setPassword(password);

        try (Connection connection = oracleConnectionService.getConnection(connectionInfo)) {
            return oracleConnectionService.getColumns(connection, schemaName, tableName);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

package com.data.tools.api.controller;

import com.data.tools.api.entity.DBConfiguration2;
import com.data.tools.api.service.DBConnectionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
public class DbConnectionController {

    @Autowired
    private DBConnectionService2 dbConnectionService;

    @GetMapping("/test")
    public String testConnection() {
        DBConfiguration2 dbConfiguration = new DBConfiguration2();
        dbConfiguration.setHost("34.125.48.246");
        dbConfiguration.setPort("1521");
        dbConfiguration.setServiceName("orcl");
        dbConfiguration.setUsername("emre");
        dbConfiguration.setPassword("ora123");

        boolean result = dbConnectionService.testConnection(dbConfiguration);

        if (result) {
            return "Status: Success!";
        } else {
            return "Status: Connection Failure";
        }
    }
}
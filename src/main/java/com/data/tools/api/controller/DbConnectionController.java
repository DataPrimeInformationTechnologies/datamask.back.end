package com.data.tools.api.controller;

import com.data.tools.api.entity.DBConfiguration2;
import com.data.tools.api.service.DBConnectionService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class DbConnectionController {

    @Autowired
    private DBConnectionService2 dbConnectionService;

    @PostMapping("/test")
    public String testConnection(@RequestBody DBConfiguration2 dbConfiguration) {

        //dbConfiguration.setHost("34.125.48.246");
        //dbConfiguration.setPort("1521");
        //dbConfiguration.setServiceName("orcl");
        //dbConfiguration.setUsername("emre");
        //dbConfiguration.setPassword("ora123");

        boolean result = dbConnectionService.testConnection(dbConfiguration);

        if (result) {
            return "Status: Success!";
        } else {
            return "Status: Connection Failure";
        }
    }
}
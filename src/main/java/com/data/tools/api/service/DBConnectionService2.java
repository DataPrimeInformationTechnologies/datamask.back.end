package com.data.tools.api.service;

import com.data.tools.api.entity.DBConfiguration2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

@Service
@Slf4j
public class DBConnectionService2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean testConnection(DBConfiguration2 dbConfiguration) {
        try {
            String url = "jdbc:oracle:thin:@" + dbConfiguration.getHost() + ":" + dbConfiguration.getPort() + ":" + dbConfiguration.getServiceName();
            jdbcTemplate.setDataSource(createDataSource(url, dbConfiguration.getUsername(), dbConfiguration.getPassword()));
            jdbcTemplate.execute("SELECT 1 FROM DUAL");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    private DataSource createDataSource(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}

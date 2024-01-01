package com.data.tools.api.service;

import com.data.tools.api.entity.DBConfiguration2;
import com.data.tools.api.entity.DBConfigurationRepository;
import com.data.tools.api.exceptions.DBNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DBConnectionTestService {

    private final JdbcTemplate jdbcTemplate;
    private DBConfigurationRepository dbConfigurationRepository;
    @Autowired
    public DBConnectionTestService(DataSource dataSource, DBConfigurationRepository dbConfigurationRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dbConfigurationRepository = dbConfigurationRepository;
    }
    // Test DB connection.
    public String testDBConnection(Long id) {
        DBConfiguration2 dbConfiguration = dbConfigurationRepository.findById(id)
                .orElseThrow(() -> new DBNotFoundException("Config not found"));

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Database Connection attempt is successfull
            return "success";
        } catch (SQLException e) {
           // Database Connection attempt is not successfull.
            return "failure: " + e.getMessage();
        }
    }
}

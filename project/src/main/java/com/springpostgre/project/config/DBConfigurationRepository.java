package com.springpostgre.project.config;

import com.springpostgre.project.config.DBConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DBConfigurationRepository extends JpaRepository<DBConfiguration, Long>{
}

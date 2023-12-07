package com.springpostgre.project.config;

import com.springpostgre.project.config.DBConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBConfigurationRepository extends JpaRepository<DBConfiguration, Long>{
}


package com.data.tools.api.entity;

import com.data.tools.api.entity.DBConfiguration2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBConfigurationRepository extends JpaRepository<DBConfiguration2, Long>{
}


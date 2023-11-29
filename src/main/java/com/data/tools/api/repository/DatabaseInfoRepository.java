package com.data.tools.api.repository;

import com.data.tools.api.entity.DatabaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo,Long> {
    List<DatabaseInfo> findBySchemaName(String schemaName);
    List<DatabaseInfo> findBySchemaNameAndTableName(String schemaName, String tableName);
}

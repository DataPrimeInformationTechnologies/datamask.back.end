package com.data.tools.api.repository;

import com.data.tools.api.entity.DatabaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
@Repository
public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo,Long> {
    @Query(value = "SELECT OWNER FROM ALL_TABLES", nativeQuery = true)
    List<DatabaseInfo> findBySchemas();
    @Query(value = "select table_name from  all_tables where owner=:schemaName", nativeQuery = true)
    List<DatabaseInfo> getByTableNameinSchemaName(@Param("schemaName") String schemaName);

    @Query(value = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_SCHEMA = :schema AND TABLE_NAME = :table", nativeQuery = true)
    List<String> getTableColumns(@Param("schema") String schema, @Param("table") String table);
}

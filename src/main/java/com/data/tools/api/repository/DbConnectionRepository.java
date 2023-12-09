package com.data.tools.api.repository;

import com.data.tools.api.entity.DbConnection;
import com.data.tools.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbConnectionRepository extends JpaRepository<DbConnection,Long> {

    DbConnection findByConnectionName(String connectionName);

    //Belirli bir kullanıcıya ait tüm konfigürasyonları getirme
    List<DbConnection> findByUser(User user);

    //Belirli bir URL'ye sahip konfigürasyonları getirme
    List<DbConnection> findByUrl(String url);

    //Belirli bir kullanıcıya ait konfigürasyon sayısını alma
    int countByUser(User user);

    //Connection Name ve URL'ye göre bulma
    DbConnection findByConnectionNameAndUrl(String connectionName, String url);

    //Connection Name içeren konfigürasyonları bulma
    List<DbConnection> findByConnectionNameContaining(String connectionName);


    //Belirli bir kullanıcının tüm konfigürasyonlarını silme
    void deleteByUser(User user);

    @Query(value = "select table_name from  all_tables where owner=:schemaName", nativeQuery = true)
    List<String> findTablesBySchemaName(@Param("schemaName")String schemaName);

    @Query(value = "SELECT COLUMN_NAME FROM ALL_TAB_COLUMNS WHERE TABLE_SCHEMA = :schemaName AND TABLE_NAME = :tableName", nativeQuery = true)
    List<String> findColumnsByTable(@Param("schemaName") String schemaName,@Param("tableName") String tableName);


}

package com.data.tools.api.repository;

import com.data.tools.api.entity.DbMigration;
import com.data.tools.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbMigrationRepository extends JpaRepository<DbMigration, Long> {

    Optional<DbMigration> findByUserAndSelectedTrue(User user);
}

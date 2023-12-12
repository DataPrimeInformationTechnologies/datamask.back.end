package com.data.tools.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.tools.api.entity.DbConfiguration;

@Repository
public interface DbConfigurationRepository extends JpaRepository<DbConfiguration, Long> {


}

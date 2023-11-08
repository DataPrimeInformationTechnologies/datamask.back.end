package com.data.tools.api.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.tools.api.entity.DbConfiguration;

@Repository
public interface DbConfigurationRepository extends JpaRepository<DbConfiguration, Long> {
	
	Page<DbConfiguration> findByUserIdAndCategory(Long userId, String category, Pageable page);
	
	Page<DbConfiguration> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);
	
	Page<DbConfiguration> findByUserId(Long userId, Pageable page);
	
	Optional<DbConfiguration> findByUserIdAndId(Long userId, Long DbConfigurationId);

	
}

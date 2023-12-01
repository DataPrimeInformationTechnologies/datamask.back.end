package com.data.tools.api.repository;

import com.data.tools.api.entity.ConnectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionInfoRepository extends JpaRepository<ConnectionInfo,Long> {

}

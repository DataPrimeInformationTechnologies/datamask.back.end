package com.data.tools.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.tools.api.entity.User2;

@Repository
public interface User2Repository extends JpaRepository<User2, Long>{
}

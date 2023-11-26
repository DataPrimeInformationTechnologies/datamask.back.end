package com.springpostgre.project.user;
import com.springpostgre.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{
}

package com.service.user.repository;

// import com.app.ecommerce.model.User;
import com.service.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

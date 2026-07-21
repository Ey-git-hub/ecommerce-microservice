package com.service.user.repository;

// import com.app.ecommerce.model.User;
import com.service.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.example.leave_application.repository;

import com.example.leave_application.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {

    Optional<ApplicationUser> findByEmail(String email);
}

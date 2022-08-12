package com.example.leave_application.repository;

import com.example.leave_application.entity.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<ApplicationRole, Integer> {
}

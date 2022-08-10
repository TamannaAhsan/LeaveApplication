package com.example.leave_application.repository;


import com.example.leave_application.entity.LeaveApplication;
import com.example.leave_application.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    Set<LeaveApplication> findByStatus(Status status);
}

package com.example.leave_application.repository;


import com.example.leave_application.entity.LeaveApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    List<LeaveApplication> findLeaveApplicationByApplicationUserId(Integer userId);

}

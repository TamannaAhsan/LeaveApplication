package com.example.leave_application.repository;

import com.example.leave_application.entity.YearlyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearLyLeaveRepository extends JpaRepository<YearlyLeave, Integer> {
}

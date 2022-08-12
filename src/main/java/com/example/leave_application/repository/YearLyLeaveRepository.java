package com.example.leave_application.repository;

import com.example.leave_application.entity.YearlyLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YearLyLeaveRepository extends JpaRepository<YearlyLeave, Integer> {
    @Query(
            value = "SELECT maximum_day FROM yearly_leave WHERE year =:year AND leave_type_id = :leaveTypeId"
            ,nativeQuery = true
    )
    int findMaximumDayByYearAndLeaveTypeId(@Param("year")int year, @Param("leaveTypeId")long leaveTypeId);
}

package com.example.leave_application.service;

import com.example.leave_application.payload.YearlyLeaveDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface YearlyLeaveService {

    YearlyLeaveDTO createYearlyLeave (YearlyLeaveDTO yearlyLeaveDTO, Integer leaveTypeId);

    void deleteYearlyLeave (Integer yearlyLeaveId);

    Set<YearlyLeaveDTO> getAllYearlyLeave();

}

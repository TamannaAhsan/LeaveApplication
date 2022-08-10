package com.example.leave_application.service;

import com.example.leave_application.payload.LeaveApplicationDTO;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {

    LeaveApplicationDTO approveLeaveApplication (LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId);

    LeaveApplicationDTO rejectLeaveApplication (LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId);
    LeaveApplicationDTO putManagerRemark (LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId);
}

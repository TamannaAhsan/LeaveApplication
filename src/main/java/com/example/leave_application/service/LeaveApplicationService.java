package com.example.leave_application.service;

import com.example.leave_application.payload.LeaveApplicationDTO;
import com.example.leave_application.payload.LeaveApplicationFilterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface LeaveApplicationService {

    LeaveApplicationDTO createLeaveApplication (LeaveApplicationDTO leaveApplicationDTO, Integer userId, Integer leaveTypeId);

    void deleteLeaveApplication (Integer leaveApplicationId);

    Set<LeaveApplicationDTO> getAllLeaveApplication ();

    LeaveApplicationDTO updateLeaveApplication (LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId);

    LeaveApplicationDTO sendLeaveApplication (LeaveApplicationDTO leaveApplicationDTO, Integer leaveApplicationId);

    List<LeaveApplicationDTO> showAllLeaveByFilter (LeaveApplicationFilterDTO leaveApplicationFilterDTO, Integer userId);

}

package com.example.leave_application.service;

import com.example.leave_application.payload.LeaveTypeDTO;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public interface LeaveTypeService {

    LeaveTypeDTO createLeaveType (LeaveTypeDTO leaveType);

    LeaveTypeDTO updateLeaveType (LeaveTypeDTO leaveType, Integer leaveTypeId);

    Set<LeaveTypeDTO> getAllType ();

    void deleteLeaveType (Integer leaveTypeId);
}
